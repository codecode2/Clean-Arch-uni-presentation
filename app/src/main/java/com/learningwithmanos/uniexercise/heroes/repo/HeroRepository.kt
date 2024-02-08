package com.learningwithmanos.uniexercise.heroes.repo


import android.util.Log
import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import com.learningwithmanos.uniexercise.heroes.usecase.ErrorHandling
import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
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


}

class HeroRepositoryImpl @Inject constructor(
    private val heroRemoteSource: HeroRemoteSource,
    private val heroLocalSource: HeroLocalSource
) : HeroRepository {



    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getHeroes(): Flow<List<Hero>> {
        val publicKey = MyApplication.preferences.getPublicKey()
        val privateKey = MyApplication.preferences.getPublicKey()

        if (publicKey == "" || privateKey == "") {

         }

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

                throw Exception("")
            }
        }
    }


}



