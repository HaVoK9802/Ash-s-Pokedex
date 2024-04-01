package com.example.ashspokedex.presentation.list

import com.example.ashspokedex.domain.models.list.PokemonListResult
import com.example.ashspokedex.domain.models.list.PokemonName
import com.example.ashspokedex.utils.RequestStatus

data class ListScreenStates(
    val pokemonListStatus : RequestStatus<PokemonListResult> = RequestStatus.Loading(),
    val pokemonList:List<PokemonName> = emptyList(),
    var pokemonListCopy:List<PokemonName> = emptyList(),
    var searchQueryValue:String = "",
    val cardTapped:Boolean = false,
    val sortingToggle:Boolean = true
)