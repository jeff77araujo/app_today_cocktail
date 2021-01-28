package com.app.todayscocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.todayscocktail.repository.CocktailsListRepository
import java.lang.IllegalArgumentException

class CocktailsListViewModelFactory(private val repository: CocktailsListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CocktailsListViewModel::class.java)) {
            return CocktailsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}