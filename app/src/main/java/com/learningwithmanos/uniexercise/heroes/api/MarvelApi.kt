package com.learningwithmanos.uniexercise.heroes.api

import com.learningwithmanos.uniexercise.heroes.source.remote.apiresults.MarvelResultCharacters

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: Long,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<MarvelResultCharacters>
}