package com.jsb.android_jetpack_practice.model.database

import androidx.room.Dao
import androidx.room.Query
import com.jsb.android_jetpack_practice.model.entities.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao
{
    @Query("SELECT * FROM BEER_TABLE")
    fun getAllBeerList(): Flow<List<Beer>>
}