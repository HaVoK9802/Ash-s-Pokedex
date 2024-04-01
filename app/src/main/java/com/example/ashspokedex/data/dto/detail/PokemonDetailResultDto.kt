package com.example.ashspokedex.data.dto.detail

data class PokemonDetailResultDto(
    val height: Int?=null,
    val id: Int?=null,
    val name: String?=null,
    val sprites: Sprites?=null,
    val stats: List<Stat>?=null,
    val types: List<Type>?=null,
    val weight: Int?=null
)