package com.app.todayscocktail.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "cocktail_base_info")
data class Cocktail(
        @PrimaryKey(autoGenerate = false)
        @Json(name = "idDrink")
        val id: Int,
        @Json(name = "strDrink")
        val name: String,
        @Json(name = "strDrinkThumb")
        val thumbUrl: String
)
