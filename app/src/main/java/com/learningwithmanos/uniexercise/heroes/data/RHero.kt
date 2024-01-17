package com.learningwithmanos.uniexercise.heroes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.learningwithmanos.uniexercise.heroes.character.Comics
import com.learningwithmanos.uniexercise.heroes.character.Thumbnail

data class RHero(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("comics")
    var availableComics: Comics,

    @SerializedName("thumbnail")
    var imageUrl: Thumbnail
)
