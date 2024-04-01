package com.example.ashspokedex.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ashspokedex.data.mappers.pokemonStatMapper
import com.example.ashspokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(pokemonRepository: PokemonRepository) : ViewModel() {
    private var _listScreenState = mutableStateOf(ListScreenStates())
    val listScreenState = _listScreenState
    val listScreenUiEvents = ListScreenUIEventsImpl(pokemonRepository)

   private fun getViewModelScope():CoroutineScope{
     return viewModelScope
   }

    init{
        //initial search
    }

    inner class ListScreenUIEventsImpl(val pokemonRepository: PokemonRepository) : ListScreenUIEvents {
        val vmScope = getViewModelScope()

        override fun onSearch(updatedPokemonQuery: String) {

            vmScope.launch {
                val listOfPokemons = try{
                    pokemonRepository.getPokemonList()
                }
                catch (e:Exception){

                }
//               _listScreenState =
            }

        }

        fun getPokemonList() {

        }

        override fun clearSearchResult() {
            TODO("Not yet implemented")
        }

        override fun sortAlphabetically() {
            TODO("Not yet implemented")
        }

        override fun cardTapped() {
            TODO("Not yet implemented")
        }
    }
}