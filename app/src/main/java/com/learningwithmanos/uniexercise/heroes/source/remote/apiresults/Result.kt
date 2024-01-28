package com.learningwithmanos.uniexercise.heroes.source.remote.apiresults

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.remote.data.RHero

data class Result(
    val comics: Comics,
    val id: Int,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,

    ){

    fun toHCharacter(): Hero {
        return Hero(id= id, name=name, availableComics = comics.available, imageUrl = resourceURI)
    }

    fun toRCharacter(): RHero {
        return RHero(id= id, name=name, availableComics = comics.available, imageUrl = resourceURI)
    }


}
