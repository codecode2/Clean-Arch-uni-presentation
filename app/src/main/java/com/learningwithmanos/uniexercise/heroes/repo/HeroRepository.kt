package com.learningwithmanos.uniexercise.heroes.repo


import Resource
import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.Tab
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject


/**
 * A repository interface that is used to coordinate the usage of the LocalSource and the
 * RemoteSource.
 */
interface HeroRepository {



    /**
     * In the scope of this method it is decided from which source the data should be retrieved from.
     * Retrieve from local if data are stored, otherwise retrieve data from remote and also store
     * the data to the local.
     *
     * @return list of heroes
     */
    suspend fun getHeroes(): Flow<List<Hero>>
    suspend fun errorHandlingHeros(): Resource<Unit>

}

class HeroRepositoryImpl @Inject constructor(
    private val heroRemoteSource: HeroRemoteSource,
    private val heroLocalSource: HeroLocalSource
) : HeroRepository {

    var publicKey = MyApplication.preferences.getPublicKey()
    var privateKey = MyApplication.preferences.getPublicKey()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getHeroes(): Flow<List<Hero>> {


        return heroLocalSource.isHeroDataStored().flatMapLatest { isHeroDataStored ->
            try {
                if (!isHeroDataStored) {
                    val heroList = heroRemoteSource.getHeroes()
                    heroLocalSource.storeHeroes(heroList)
                    flowOf(heroList)

                } else {
                    heroLocalSource.getHeroes()
                }
            } catch (e: Exception) {

                throw Exception("Wrong Keys")
            }
        }
    }



    override suspend fun errorHandlingHeros(): Resource<Unit> {
        return try {

            val isHeroDataStored: Boolean = heroLocalSource.isHeroDataStored().first()

            if (publicKey == "" || privateKey == "") {
                return Resource.Error("Your API keys are empty!")
            }

            if (!isHeroDataStored) {
                val heroList = heroRemoteSource.getHeroes()
                heroLocalSource.storeHeroes(heroList)
                flowOf(heroList)

            } else {
                heroLocalSource.getHeroes()
            }

            Resource.Error("")
        } catch (e: Exception) {
            Resource.Error("Your API keys are wrong or expired!")
        }
    }

}






