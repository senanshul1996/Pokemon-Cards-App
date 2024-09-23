package com.example.pokemoncardapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonCards(cards: List<PokemonEntity>)


    @Query("SELECT * FROM pokemon")
    fun getAllPokemonCards(): LiveData<List<PokemonEntity>>

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun getPokemonCount(): Int


}