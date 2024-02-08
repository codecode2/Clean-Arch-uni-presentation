package com.learningwithmanos.uniexercise.heroes.api.response


data class Result(
    val comics: Comics,
    val id: Int,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,

    )


