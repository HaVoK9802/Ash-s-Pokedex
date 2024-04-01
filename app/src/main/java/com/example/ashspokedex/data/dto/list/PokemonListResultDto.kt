package com.example.ashspokedex.data.dto.list

data class PokemonListResultDto(
    val count: Int?=null,
    val next: Any?=null,
    val previous: Any?=null,
    val results: List<Result>?=null
)