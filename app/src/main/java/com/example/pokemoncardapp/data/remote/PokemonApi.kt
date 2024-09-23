package com.example.pokemoncardapp.data.remote




import com.example.pokemoncardapp.data.model.PokemonCardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("v2/cards")
    suspend fun getPokemonData(
        @Query("pageSize") pageSize: String
    ): Response<PokemonCardResponse>
}
