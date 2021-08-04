package com.jsb.android_jetpack_practice.model.network

import com.jsb.android_jetpack_practice.model.entities.RandomBeer
import com.jsb.android_jetpack_practice.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BeerApi
{
    @GET(Constants.API_ENDPOINT)
    fun getRandomBeer(): Single<RandomBeer>
}