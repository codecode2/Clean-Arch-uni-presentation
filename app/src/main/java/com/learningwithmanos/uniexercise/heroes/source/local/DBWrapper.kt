package com.learningwithmanos.uniexercise.heroes.source.local

import android.util.Log
import androidx.compose.material3.MaterialTheme
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.LHero
import com.learningwithmanos.uniexercise.heroes.data.RHero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import com.learningwithmanos.uniexercise.heroes.source.local.MarvelDao
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty

interface DBWrapper {
    suspend fun isHeroDataStored(): Flow<Boolean>
    suspend fun storeHeroes(heroes: List<Hero>)
    fun getHeroes(): Flow<List<Hero>>
}

class DummyDBWrapper @Inject constructor(private val marvelDao : MarvelDao) : DBWrapper {
    override suspend fun isHeroDataStored(): Flow<Boolean> {
        val heroList = marvelDao.getAllHeroes().firstOrNull() ?: emptyList()
        val heroes = heroList.map { it.mapToHero() }
        Log.d("Testing", heroes.isNotEmpty().toString())
        return flowOf(heroes.isNotEmpty())
    }

    override suspend fun storeHeroes(heroes: List<Hero>) {

        val hero: List<LHero> = heroes.map {
            it.mapToLHero()
        }

            marvelDao.insertCharacterList(hero)

    }

    override fun getHeroes(): Flow<List<Hero>> {
        val herolist = marvelDao.getAllHeroes().map { list-> list.map { it.mapToHero() } }
        Log.d("Testing", "Topiki vasi")
        return herolist
    }
}





fun LHero.mapToHero() = Hero (
    id = this.id,
    name = this.name,
    availableComics = this.availableComics,
    imageUrl = this.imageUrl
)

fun Hero.mapToLHero() = LHero (
    id = this.id,
    name = this.name,
    availableComics = this.availableComics,
    imageUrl = this.imageUrl
)
