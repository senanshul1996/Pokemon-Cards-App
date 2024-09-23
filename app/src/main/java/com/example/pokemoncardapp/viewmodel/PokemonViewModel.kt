package com.example.pokemoncardapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemoncardapp.data.local.PokemonEntity
import com.example.pokemoncardapp.data.model.PokemonCardResponse
import com.example.pokemoncardapp.data.remote.RetrofitInstance
import com.example.pokemoncardapp.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {


        private val _pokemonData = MutableLiveData<PokemonCardResponse?>()
        val pokemonData: LiveData<PokemonCardResponse?> = _pokemonData




    fun fetchPokemon() {

        viewModelScope.launch {

            try {
                val response = pokemonRepository.getPokemon()
                if (response.isSuccessful) {

                    _pokemonData.postValue(response.body())
                    Log.e("ViewModel", " Data Updated: ${_pokemonData.value}")


                    val pokemonEntities = response.body()?.data?.map { card ->
                        PokemonEntity(
                            name = card.name,
                            smallImg = card.images.small,
                            largeImg = card.images.large,
                            types = card.types.joinToString(", "),
                            subType = card.subtypes.joinToString(", "),
                            level = card.level ?: "NA",
                            hp = card.hp ?: "NA",
                            attack = card.attacks?.get(0)?.name ?: "NA",
                            weakness = card.weaknesses?.get(0)?.type?: "NA",
                            abilities = card.abilities?.get(0)?.name?:"NA",
                            resistance = card.resistances?.get(0)?.type?:"NA"
                        )
                    } ?: emptyList()



                    pokemonRepository.insertPokemonCards(pokemonEntities)


                } else {
                    Log.e("ViewModel", "Failed to fetch  data: ${response.errorBody()?.string()}")
                }
            }catch (e : Exception){
                Log.e("ViewModel", " Error: ${e}")
            }
        }
    }


    fun getAllPokemonCards(): LiveData<List<PokemonEntity>> {
        return pokemonRepository.getAllPokemonCards()
    }
}
