package com.example.pokemoncardapp.utils

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.pokemoncardapp.Pages
import com.example.pokemoncardapp.data.local.PokemonDao
import com.example.pokemoncardapp.data.local.PokemonEntity
import com.example.pokemoncardapp.data.model.PokemonCard
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DataManager {


    var data = mutableStateListOf<PokemonEntity>()
    var currentCard: PokemonEntity? = null
    var currentPage = mutableStateOf(Pages.LISTING)
    var isDataLoaded = mutableStateOf(false)


    fun switchPages(card: PokemonEntity?) {
        if (currentPage.value == Pages.LISTING) {
            currentCard = card
            currentPage.value = Pages.DETAIL
        } else {
            currentPage.value = Pages.LISTING
        }
    }
}