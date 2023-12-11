package com.learningwithmanos.uniexercise.heroes.api

import com.learningwithmanos.uniexercise.heroes.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APISercice {
        val instance: MarvelApi


    init {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        instance = retrofit.create(MarvelApi::class.java)


        }

}