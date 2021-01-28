package com.app.todayscocktail.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.todayscocktail.network.Cocktail
import com.app.todayscocktail.network.CocktailsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CocktailsListRepository() {
    // guarda as informações
    private val cocktailListResponse = MutableLiveData<List<Cocktail>>()

    // expõe as informações
    val cocktailsList: LiveData<List<Cocktail>>
        get() = cocktailListResponse

    init { getCocktails() }

    // efetua requisição dos dados dos coquetéis atráves do retrofit
    private fun getCocktails() {
        // executando tarefas fora da main thread, porque vamos fazer uma requisição de rede
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listResult = CocktailsApi.retrofitService.getNonAlcoholicCocktails().cocktailsList
                cocktailListResponse.postValue(listResult)
            } catch (e: Exception) {
                Log.i("Service error", "${e.message}")
                withContext(Dispatchers.Main) {
                    cocktailListResponse.postValue(listOf())
                }
            }
        }
    }
}