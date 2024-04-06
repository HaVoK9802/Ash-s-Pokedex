package com.example.ashspokedex.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ashspokedex.domain.models.detail.PokemonDetailResult
import com.example.ashspokedex.domain.repository.PokemonRepository
import com.example.ashspokedex.utils.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(val pokemonRepository:PokemonRepository): ViewModel(){



    var _detailScreenStates by mutableStateOf(DetailScreenStates())
    val detailScreenStates by mutableStateOf(_detailScreenStates)



    fun fetchPokemonStats(name:String){

        viewModelScope.launch(Dispatchers.IO) {
             try {

                val res = pokemonRepository.getPokemonDetails(name)
                withContext(Dispatchers.Main){
                    _detailScreenStates = _detailScreenStates.copy(
                        pokeDetailStatus = RequestStatus.Success(res),
                        statResult = res.stats,
                        types = res.types,
                        height = res.height,
                        weight = res.weight,
                        pokemonName = res.name,
                        imgUrl = res.imageUrl
                    )
                }
            } catch (e: IOException) {
                RequestStatus.Error<PokemonDetailResult>("Not Able to fetch")
            }
        }

    }
}