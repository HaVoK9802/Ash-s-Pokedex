package com.example.ashspokedex.data.repository

import com.example.ashspokedex.data.mappers.toPokemonDetail
import com.example.ashspokedex.data.mappers.toPokemonListResult
import com.example.ashspokedex.data.remote.LIMIT
import com.example.ashspokedex.data.remote.OFFSET
import com.example.ashspokedex.data.remote.PokeApi
import com.example.ashspokedex.domain.models.detail.PokemonDetailResult
import com.example.ashspokedex.domain.models.list.PokemonListResult
import com.example.ashspokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(val pokemonApiService:PokeApi):PokemonRepository {

    override suspend fun getPokemonList():PokemonListResult {
        return pokemonApiService.getPokemonList(OFFSET.toString(), LIMIT.toString()).toPokemonListResult()
    }

    override suspend fun getPokemonDetails(name:String):PokemonDetailResult{
       return pokemonApiService.getPokemonDetails(name).toPokemonDetail()
    }

}