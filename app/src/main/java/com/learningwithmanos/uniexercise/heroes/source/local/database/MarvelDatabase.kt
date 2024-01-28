package com.learningwithmanos.uniexercise.heroes.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learningwithmanos.uniexercise.heroes.source.local.data.LHero
import com.learningwithmanos.uniexercise.heroes.source.local.database.MarvelDao

@Database(entities = [LHero::class], version = 1,exportSchema = false)
abstract class MarvelDatabase: RoomDatabase() {
    abstract  fun marvelDao(): MarvelDao

}