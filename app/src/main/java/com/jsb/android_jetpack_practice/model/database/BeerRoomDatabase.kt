package com.jsb.android_jetpack_practice.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jsb.android_jetpack_practice.model.entities.Beer

@Database(entities = [Beer::class], version = 1)
abstract class BeerRoomDatabase: RoomDatabase()
{
    abstract fun beerDao(): BeerDao

    companion object{
        @Volatile
        private var INSTANCE: BeerRoomDatabase? = null

        fun getDatabase(context: Context): BeerRoomDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BeerRoomDatabase::class.java,
                    "beer_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}