package com.example.pokemoncardapp.data.repository



import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pokemoncardapp.data.local.PokemonDao
import com.example.pokemoncardapp.data.local.PokemonEntity
import com.example.pokemoncardapp.data.model.PokemonCardResponse
import com.example.pokemoncardapp.data.remote.PokemonApi
import retrofit2.Response

class PokemonRepository(private val api: PokemonApi,private val pokemonDao: PokemonDao) {
    suspend fun getPokemon(): Response<PokemonCardResponse> {
        return api.getPokemonData( "20")
    }

    suspend fun insertPokemonCards(cards: List<PokemonEntity>) {
        val count = pokemonDao.getPokemonCount()
        if (count == 0) {
            pokemonDao.insertPokemonCards(cards)
        }else {
            Log.d("Repository", "Database is not empty. Data not inserted.")
        }
    }

    fun getAllPokemonCards(): LiveData<List<PokemonEntity>> {
        return pokemonDao.getAllPokemonCards()
    }
}



