package com.learningwithmanos.uniexercise.heroes.character

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.RHero

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
){

    fun toHCharacter():Hero {
        return Hero(id= id, name=name, availableComics = comics.available, imageUrl = resourceURI)
    }

    fun toRCharacter(): RHero {
        return RHero(id= id, name=name, availableComics = comics, imageUrl = thumbnail)
    }


}
