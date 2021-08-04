package com.jsb.android_jetpack_practice.model.database

import com.jsb.android_jetpack_practice.model.entities.Beer
import kotlinx.coroutines.flow.Flow

class BeerRepository(private val beerDao: BeerDao)
{
    val allBeerList: Flow<List<Beer>> = beerDao.getAllBeerList()
}