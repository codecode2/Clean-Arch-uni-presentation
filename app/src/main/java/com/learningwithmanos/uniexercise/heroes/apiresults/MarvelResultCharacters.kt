package com.learningwithmanos.uniexercise.heroes.apiresults

data class MarvelResultCharacters(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)