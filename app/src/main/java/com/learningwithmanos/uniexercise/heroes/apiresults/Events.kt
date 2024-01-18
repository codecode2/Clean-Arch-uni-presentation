package com.learningwithmanos.uniexercise.heroes.apiresults

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)