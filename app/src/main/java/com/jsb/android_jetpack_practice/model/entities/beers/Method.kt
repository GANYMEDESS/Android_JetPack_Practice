package com.jsb.android_jetpack_practice.model.entities.beers

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: String
)