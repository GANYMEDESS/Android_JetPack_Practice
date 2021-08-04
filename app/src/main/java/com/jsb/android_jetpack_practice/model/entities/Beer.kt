package com.jsb.android_jetpack_practice.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer_table")
data class Beer(
    @ColumnInfo val image: String,
    @ColumnInfo(name = "image_url") val imageSource: String,
    @ColumnInfo val title: String,
    @ColumnInfo val ph: Float,
    @ColumnInfo val tagline: String,
    @ColumnInfo val attenuation_level: Float,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
