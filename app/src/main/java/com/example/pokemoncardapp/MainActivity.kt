package com.example.pokemoncardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokemoncardapp.data.local.PokemonDao
import com.example.pokemoncardapp.data.local.PokemonDatabase
import com.example.pokemoncardapp.data.remote.RetrofitInstance
import com.example.pokemoncardapp.data.repository.PokemonRepository
import com.example.pokemoncardapp.ui.screens.PokemonDetailScreen
import com.example.pokemoncardapp.ui.screens.PokemonListScreen
import com.example.pokemoncardapp.utils.DataManager
import com.example.pokemoncardapp.viewmodel.PokemonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var pokemonViewModel: PokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val pokemonDao = PokemonDatabase.getDatabase(applicationContext).pokemonDao()
        val repository = PokemonRepository(RetrofitInstance.api, pokemonDao)
        pokemonViewModel = PokemonViewModel(repository)

        CoroutineScope(Dispatchers.IO).launch {
            pokemonViewModel.fetchPokemon()
        }

        setContent {
            App(pokemonViewModel)
        }
    }
}

@Composable
fun App( pokemonViewModel: PokemonViewModel) {

    val pokemonCards by pokemonViewModel.getAllPokemonCards().observeAsState(emptyList())


    LaunchedEffect(pokemonCards) {
        if (pokemonCards.isNotEmpty()) {
            DataManager.data.clear()
            DataManager.data.addAll(pokemonCards)
            DataManager.isDataLoaded.value = true
        }
    }


    if (DataManager.isDataLoaded.value) {
        if (DataManager.currentPage.value == Pages.LISTING) {
            PokemonListScreen(data = DataManager.data) { card ->
                DataManager.switchPages(card)
            }
        } else {
            DataManager.currentCard?.let { card ->
                PokemonDetailScreen(card = card)
            }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


enum class Pages{
    LISTING,
    DETAIL
}

