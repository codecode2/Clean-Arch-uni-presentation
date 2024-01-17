package com.learningwithmanos.uniexercise.heroes.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.learningwithmanos.uniexercise.heroes.data.LHero


    @Dao
    interface MarvelDao {


        @Insert
    suspend fun insertCharacterList(hero: List<LHero>)



}