package com.jsb.android_jetpack_practice.model.network

import com.jsb.android_jetpack_practice.model.entities.RandomBeer
import com.jsb.android_jetpack_practice.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BeerApiService
{
    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(BeerApi::class.java)

    fun getRandomBeer(): Single<RandomBeer> {
        return api.getRandomBeer()
    }
}