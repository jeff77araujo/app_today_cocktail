package com.app.todayscocktail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.todayscocktail.R
import com.app.todayscocktail.network.CocktailsApi
import com.app.todayscocktail.repository.CocktailsListRepository
import com.app.todayscocktail.viewmodel.CocktailsListViewModel
import com.app.todayscocktail.viewmodel.CocktailsListViewModelFactory
import com.wcc.todayscocktail.data.CocktailDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseDao = CocktailDatabase.getDatabase(this, CoroutineScope(Dispatchers.IO)).cocktailDao()
        val remoteService = CocktailsApi.retrofitService
        val repository = CocktailsListRepository(databaseDao, remoteService)

        val viewModelFactory = CocktailsListViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(CocktailsListViewModel::class.java)
        val list = viewModel.cocktailList

        val recycleView = findViewById<RecyclerView>(R.id.idRecycleView)
        val adapter = CocktailsListAdapter()
        recycleView.adapter = adapter

        list.observe(this, Observer {
            adapter.data = it
            recycleView.visibility = View.VISIBLE
            findViewById<ProgressBar>(R.id.loadingDrinksIndicator).visibility = View.GONE
        })
    }
}