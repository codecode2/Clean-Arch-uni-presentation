package com.learningwithmanos.uniexercise.heroes.source.remote

import android.util.Log
import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.api.MarvelApi
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.utils.Constants
import javax.inject.Inject

interface RestFrameworkWrapper{
    suspend fun getHeroes(): List<Hero>
}

class DummyRestFrameworkWrapper @Inject constructor(
    private val marvelApi: MarvelApi

): RestFrameworkWrapper {
    override suspend fun getHeroes(): List<Hero> {
        val PUBLIC_KEY = MyApplication.preferences.getPublicKey()
        val PRIVATE_KEY = MyApplication.preferences.getPrivateKey()

        val response = marvelApi.getAllCharacters(
            apiKey = PUBLIC_KEY,
            timestamp = Constants.timeStamp,
            hash = Constants.hash(),
            limit =Constants.limit,
            offset = 0
        )

        if (response.isSuccessful) {

            val key= PUBLIC_KEY
            val key2= PRIVATE_KEY
            Log.d("PUBLICKEY", "$key")
            Log.d("PRIVATEKEY", "$key2")
            Log.d("Testing", "remote call again")
            return response.body()?.data?.results?.map { marvelCharacter ->
                Hero(
                    id = marvelCharacter.id,
                    name = marvelCharacter.name,
                    availableComics = marvelCharacter.comics.available,
                    imageUrl = "${marvelCharacter.thumbnail.path}/portrait_incredible.${marvelCharacter.thumbnail.extension}"
                )


            } ?: emptyList()


        } else {

            throw Exception("Error fetching heroes: ${response.code()} - ${response.message()}")
        }
    }






}
