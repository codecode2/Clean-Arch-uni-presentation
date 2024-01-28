package com.learningwithmanos.uniexercise.heroes.apiresults

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.RHero

data class Result(
    val comics: Comics,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,

){

    fun toHCharacter():Hero {
        return Hero(id= id, name=name, availableComics = comics.available, imageUrl = resourceURI)
    }

    fun toRCharacter(): RHero {
        return RHero(id= id, name=name, availableComics = comics.available, imageUrl = resourceURI)
    }


}
