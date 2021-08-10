package com.jsb.android_jetpack_practice.model.entities

object RandomBeer
{
    class Beers: ArrayList<BeersItem>()

    data class BeersItem(
        val id: Int,
        val name: String,
        val tagline: String,
        val first_brewed: String,
        val description: String,
        val image_url: String,
        val abv: Double,
        val ibu: Int,
        val target_fg: Int,
        val target_og: Double,
        val ebc: Int,
        val srm: Double,
        val ph: Double,
        val attenuation_level: Double,
        val volume: Volume,
        val boil_volume: BoilVolume,
        val method: Method,
        val ingredients: Ingredients,
        val food_pairing: List<String>,
        val brewers_tips: String,
        val contributed_by: String
    )

    data class BoilVolume(
        val unit: String,
        val value: Int
    )

    data class Ingredients(
        val hops: List<Hop>,
        val malt: List<Malt>,
        val yeast: String
    )

    data class Method(
        val fermentation: Fermentation,
        val mash_temp: List<MashTemp>,
        val twist: String
    )

    data class Volume(
        val unit: String,
        val value: Int
    )

    data class Hop(
        val add: String,
        val amount: Amount,
        val attribute: String,
        val name: String
    )

    data class Malt(
        val amount: AmountX,
        val name: String
    )

    data class Fermentation(
        val temp: Temp
    )

    data class MashTemp(
        val duration: Int,
        val temp: TempX
    )

    data class Temp(
        val unit: String,
        val value: Int
    )

    data class TempX(
        val unit: String,
        val value: Int
    )

    data class Amount(
        val unit: String,
        val value: Double
    )

    data class AmountX(
        val unit: String,
        val value: Double
    )
}