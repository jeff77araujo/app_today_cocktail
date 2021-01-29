package com.app.todayscocktail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.todayscocktail.entity.Cocktail
import com.app.todayscocktail.repository.CocktailsListRepository

class CocktailsListViewModel(private val repository: CocktailsListRepository): ViewModel() {
    val cocktailList: LiveData<List<Cocktail>>
        get() = repository.cocktailsList
}