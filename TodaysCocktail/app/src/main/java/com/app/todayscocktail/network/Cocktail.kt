package com.app.todayscocktail.network

import com.squareup.moshi.Json

data class Cocktail(
    @Json(name = "idDrink")
    val id: Int,

    @Json(name = "strDrink")
    val name: String,

    @Json(name = "strDrinkThumb")
    val thumbUrl: String
)
