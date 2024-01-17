package com.learningwithmanos.uniexercise.heroes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterTable")
data class LHero(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "character_name")
    var name: String,

    @ColumnInfo(name = "character_comics")
    var availableComics: Int,

    @ColumnInfo(name = "character_image")
    var imageUrl: String
)