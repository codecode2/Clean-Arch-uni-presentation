package com.learningwithmanos.uniexercise.heroes.api

import com.learningwithmanos.uniexercise.heroes.character.MarvelResultCharacters

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters (

        @Query("apikey") apikey: String  ,
        @Query("ts") ts:String,
        @Query("hash") hash: String ,
        @Query("limit") limit: Int ,
        @Query("offset")offset: Int
    ): Response<MarvelResultCharacters>



}