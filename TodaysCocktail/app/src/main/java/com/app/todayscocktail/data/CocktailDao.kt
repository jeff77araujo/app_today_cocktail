package com.app.todayscocktail.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.todayscocktail.entity.Cocktail

@Dao
interface CocktailDao {
    @Insert
    suspend fun insert(vararg cocktail: Cocktail)

    @Query("SELECT * FROM cocktail_base_info ORDER BY id ASC")
    suspend fun getAll(): List<Cocktail>
}