package com.example.ashspokedex.di

import com.example.ashspokedex.data.remote.BASE_URL
import com.example.ashspokedex.data.remote.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PokemonApiModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun pokemonApiServiceProvider(retrofit: Retrofit):PokeApi{
        return retrofit.create(PokeApi::class.java)
    }

}