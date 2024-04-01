package com.example.ashspokedex.domain.repository

import com.example.ashspokedex.domain.models.detail.PokemonDetailResult
import com.example.ashspokedex.domain.models.list.PokemonListResult

interface PokemonRepository {
    suspend fun getPokemonList():PokemonListResult
    suspend fun getPokemonDetails(name:String):PokemonDetailResult
}