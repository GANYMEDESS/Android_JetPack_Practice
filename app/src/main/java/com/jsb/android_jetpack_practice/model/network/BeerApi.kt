package com.jsb.android_jetpack_practice.model.network

import com.jsb.android_jetpack_practice.model.entities.beers.Beers
import com.jsb.android_jetpack_practice.model.entities.RandomBeer
import com.jsb.android_jetpack_practice.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BeerApi
{
    @GET(Constants.API_RANDOM_BEER_ENDPOINT)
    fun getRandomBeer(): Single<RandomBeer.Beers>

    @GET(Constants.API_BEER_LIST_ENDPOINT)
    fun getBeerList(): Single<Beers>
}