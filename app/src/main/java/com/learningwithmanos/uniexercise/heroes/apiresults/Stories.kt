package com.learningwithmanos.uniexercise.heroes.apiresults

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)