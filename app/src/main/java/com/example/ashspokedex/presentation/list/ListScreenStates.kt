package com.example.ashspokedex.presentation.list

import com.example.ashspokedex.domain.models.list.PokemonListResult
import com.example.ashspokedex.domain.models.list.PokemonNameAndId
import com.example.ashspokedex.utils.RequestStatus

data class ListScreenStates(
    val pokemonListResultStatus: RequestStatus<PokemonListResult> = RequestStatus.Loading(),
    val pokemonList:List<PokemonNameAndId> = emptyList(),
    var pokemonListCopy: List<PokemonNameAndId> = emptyList(),
    var searchQueryValue:String = "",
    val sortingToggle:Boolean = true
)