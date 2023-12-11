package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.Hero
import javax.inject.Inject

interface RestFrameworkWrapper{
    suspend fun getHeroes(): List<Hero>
}

class DummyRestFrameworkWrapper @Inject constructor(): RestFrameworkWrapper {
    override suspend fun getHeroes(): List<Hero> {


        

        return listOf()
    }
}