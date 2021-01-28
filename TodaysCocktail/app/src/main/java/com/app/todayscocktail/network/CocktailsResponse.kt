package com.app.todayscocktail.network

import com.squareup.moshi.Json

data class CocktailsResponse(
    @Json(name = "drinks")
    val cocktailsList: List<Cocktail>
)