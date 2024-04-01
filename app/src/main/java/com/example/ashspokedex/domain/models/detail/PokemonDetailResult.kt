package com.example.ashspokedex.domain.models.detail

data class PokemonDetailResult(
    val name : String,
    val imageUrl : String,
    val height : Int,
    val weight: Int,
    val types : List<PokeType>,
    val stats : List<PokeStats>
)