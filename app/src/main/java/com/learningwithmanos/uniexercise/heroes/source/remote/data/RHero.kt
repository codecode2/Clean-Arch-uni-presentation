package com.learningwithmanos.uniexercise.heroes.source.remote.data

import com.google.gson.annotations.SerializedName

data class RHero(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("comics")
    var availableComics: Int,

    @SerializedName("thumbnail")
    var imageUrl: String
)
