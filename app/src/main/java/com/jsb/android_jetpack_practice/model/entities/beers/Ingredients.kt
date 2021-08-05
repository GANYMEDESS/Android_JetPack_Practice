package com.jsb.android_jetpack_practice.model.entities.beers

data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)