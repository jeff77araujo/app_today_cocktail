package com.app.todayscocktail.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.todayscocktail.data.CocktailDao
import com.app.todayscocktail.entity.Cocktail
import com.app.todayscocktail.network.CocktailsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CocktailsListRepository(private val cocktailDao: CocktailDao, private val cocktailApi: CocktailsApiService) {
    // guarda as informações
    private val cocktailListResponse = MutableLiveData<List<Cocktail>>()

    // expõe as informações
    val cocktailsList: LiveData<List<Cocktail>>
        get() = cocktailListResponse

    init { getCocktails() }

    // efetua requisição dos dados dos coquetéis atráves do retrofit
    private fun getCocktails() {
        CoroutineScope(Dispatchers.IO).launch {
            val listFromDB = cocktailDao.getAll()
            if(listFromDB.isNotEmpty()) {
                cocktailListResponse.postValue(listFromDB)
            } else {
                getCocktailsFromRemote()
            }
        }
    }

    private fun getCocktailsFromRemote() {
        // executando tarefas fora da main thread, porque vamos fazer uma requisição de rede
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listResult = cocktailApi.getNonAlcoholicCocktails().cocktailsList
                saveRemoteDataAtDatabase(listResult)

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    cocktailListResponse.postValue(listOf())
                }
            }
        }
    }

    private fun saveRemoteDataAtDatabase(cocktailList: List<Cocktail>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (cocktail in cocktailList) {
                cocktailDao.insert(cocktail)
            }
        }
    }
}