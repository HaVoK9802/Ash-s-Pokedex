package com.example.ashspokedex.data.remote

import androidx.compose.ui.geometry.Offset
import com.example.ashspokedex.data.dto.detail.PokemonDetailResultDto
import com.example.ashspokedex.data.dto.list.PokemonListResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: String,
        @Query("limit") limit:String
    ):PokemonListResultDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name:String
    ):PokemonDetailResultDto
}