package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.api.MarvelApi
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.utils.Constants
import javax.inject.Inject

interface RestFrameworkWrapper{
    suspend fun getHeroes(): List<Hero>
}

class DummyRestFrameworkWrapper @Inject constructor(
    private val MarvelApi: MarvelApi

): RestFrameworkWrapper {
    override suspend fun getHeroes(): List<Hero> {

        val response = MarvelApi.getAllCharacters(
            apiKey = Constants.API_KEY,
            timestamp = Constants.timeStamp,
            hash = Constants.hash(),
            limit =Constants.limit,
            offset= 0

        )

        if (response.isSuccessful) {
            return response.body()?.data?.results?.map { marvelCharacter ->
                Hero(
                    id = marvelCharacter.id,
                    name = marvelCharacter.name,
                    availableComics = marvelCharacter.comics.available,
                    imageUrl = "${marvelCharacter.thumbnail.path}/portrait_incredible.${marvelCharacter.thumbnail.extension}"
                )
            } ?: emptyList()
        } else {
            // Consider a more nuanced approach to error handling
            throw Exception("Error fetching heroes: ${response.code()} - ${response.message()}")
        }
    }

}
