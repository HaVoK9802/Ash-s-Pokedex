package com.example.ashspokedex.di

import com.example.ashspokedex.data.repository.PokemonRepositoryImpl
import com.example.ashspokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonRepositoryModule {
    @Binds
    abstract fun bindPokemonRepository(pokemonRepository: PokemonRepositoryImpl):PokemonRepository
}