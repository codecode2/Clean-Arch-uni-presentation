package com.learningwithmanos.uniexercise.heroes.api

import com.learningwithmanos.uniexercise.heroes.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APISercice {

    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    val marvelApi: MarvelApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
}