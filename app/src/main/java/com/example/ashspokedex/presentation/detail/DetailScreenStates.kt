package com.example.ashspokedex.presentation.detail

import com.example.ashspokedex.domain.models.detail.PokeStats
import com.example.ashspokedex.domain.models.detail.PokeType
import com.example.ashspokedex.domain.models.detail.PokemonDetailResult
import com.example.ashspokedex.utils.RequestStatus

data class DetailScreenStates(
    var pokeDetailStatus:RequestStatus<PokemonDetailResult> = RequestStatus.Loading(),
    val height:Int=0,
    val weight:Int=0,
    val pokemonName:String="",
    val statResult: List<PokeStats> = emptyList(),
    val types:List<PokeType> = emptyList(),
    val imgUrl:String = ""
    )