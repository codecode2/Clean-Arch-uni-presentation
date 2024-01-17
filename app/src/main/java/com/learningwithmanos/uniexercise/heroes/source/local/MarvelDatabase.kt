package com.learningwithmanos.uniexercise.heroes.source.local

import androidx.room.Database
import com.learningwithmanos.uniexercise.heroes.data.LHero
import androidx.room.RoomDatabase

@Database(entities = [LHero::class], version = 1,exportSchema = false)
abstract class MarvelDatabase: RoomDatabase() {
    abstract  fun marvelDao(): MarvelDao

}