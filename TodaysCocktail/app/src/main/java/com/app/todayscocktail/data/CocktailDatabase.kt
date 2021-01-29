package com.wcc.todayscocktail.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.todayscocktail.data.CocktailDao
import com.app.todayscocktail.entity.Cocktail
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class CocktailDatabase: RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CocktailDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CocktailDatabase::class.java,
                        "learned_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}