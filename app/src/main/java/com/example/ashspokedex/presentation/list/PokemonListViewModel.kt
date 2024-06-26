package com.example.ashspokedex.presentation.list

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ashspokedex.data.mappers.pokemonStatMapper
import com.example.ashspokedex.domain.models.list.PokemonListResult
import com.example.ashspokedex.domain.repository.PokemonRepository
import com.example.ashspokedex.utils.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PokemonListViewModel @Inject constructor(pokemonRepository: PokemonRepository) : ViewModel() {
//    var searchedQuery by mutableStateOf("")
//    val listScreenState by mutableStateOf(_listScreenState)

    var _listScreenState by mutableStateOf(ListScreenStates())
    var cardTapped:Boolean = false
    val listScreenUiEvents = ListScreenUIEventsImpl(pokemonRepository)


    inner class ListScreenUIEventsImpl(private val pokemonRepository: PokemonRepository) : ListScreenUIEvents {

        override fun setCardTappedToTrue(cardTappedValue:Boolean){
            //this will only last some time before revertCardTapped() does it's job
            cardTapped = true
        }

//        override fun returnUpdatedQuery(query: String){
////            viewModelScope.launch(Dispatchers.Main){
////                delay(200)
////                searchedQuery = query
////            }
//            searchedQuery = query
//        }
        override fun liveSearch(updatedPokemonQuery: String) {
            //----SYNCHRONOUS EVENTS

//             viewModelScope.launch{

            _listScreenState = _listScreenState.copy(searchQueryValue = updatedPokemonQuery)

            //once coroutineScope is involved, it will become asynchronous. Thus will lead to inconsistencies in the TextField

//             }

            //----ASYNCHRONOUS EVENTS
           viewModelScope.launch(Dispatchers.Default){
               if(updatedPokemonQuery==""){
                   val originalList = _listScreenState.pokemonList
                   withContext(Dispatchers.Main){
                       _listScreenState = _listScreenState.copy(
                           pokemonListCopy = originalList,
                           sortingToggle = true,
//                           searchQueryValue = ""
                       )

                   }
                   //CoroutineScope is a function that is used create a scope for coroutines to be started and controlled
                   //takes in the CoroutineContext as argument and uses launch and async (coroutine builders) as extension functions
                   val scope = CoroutineScope(
                       //CoroutineContext consists of
                       // Job(), - controls the lifecyle of the coroutine, unique to a given coroutine
                       // Dispatcher, - dispatches work to the appropiate thread
                       // CoroutineName - gives coroutine a name for error handling purposes
                       // and Exception Handler - ...

                       Job()+
                               Dispatchers.Main+
                               CoroutineName("hello")

//                   +CoroutineExceptionHandler()
                   )
                   val job = scope.launch {  }



               }
               else {

                   val filterByStartingStringMatch = _listScreenState.pokemonList.filter { it.name.startsWith(updatedPokemonQuery) }
                   val filterByRelevance =  _listScreenState.pokemonList.filter{ updatedPokemonQuery in it.name }
                   if (filterByStartingStringMatch.isNotEmpty()) {
                   val copyList = filterByStartingStringMatch.toMutableList()
                       for(pokemon in filterByRelevance){
                           if(!copyList.contains(pokemon)){
                               copyList.add(pokemon) } }
                       withContext(Dispatchers.Main){
                           _listScreenState = _listScreenState.copy(
                               pokemonListCopy = copyList.toList(),
                               sortingToggle = true,
//                               searchQueryValue = updatedPokemonQuery
                           )
                       }
                   } else{
                         withContext(Dispatchers.Main){
                             _listScreenState = _listScreenState.copy(
                                 pokemonListCopy = filterByRelevance,
                                 sortingToggle = true,
//                                 searchQueryValue = updatedPokemonQuery
                             )
                         }
                   }
               }
           }
        }

        fun getPokemonList() {
            viewModelScope.launch(Dispatchers.IO){
                  try {
                      val pokemonListResult = pokemonRepository.getPokemonList()
                      withContext(Dispatchers.Main) {
                          _listScreenState = _listScreenState.copy(
                              pokemonListResultStatus = RequestStatus.Success(pokemonListResult),
                              pokemonList = pokemonListResult.pokemonList
                          )
                      }
                      liveSearch("mega")
                  }
                  catch (e:Exception){
                      withContext(Dispatchers.Main) {
                          _listScreenState = _listScreenState.copy(
                              pokemonListResultStatus = RequestStatus.Error(e.toString())
                          )
                      }
                  }

            }
        }

        override fun clearSearch() {
            liveSearch("")
        }

        override fun sortingFeature(newval:Boolean) {
            viewModelScope.launch(Dispatchers.Default){
                val filterByStartingStringMatch = _listScreenState.pokemonList.filter { it.name.startsWith(_listScreenState.searchQueryValue) }
                val filterByRelevance =  _listScreenState.pokemonList.filter {_listScreenState.searchQueryValue in it.name }

                if(_listScreenState.sortingToggle==true){
                    if (_listScreenState.searchQueryValue == "") {

                        _listScreenState = _listScreenState.copy(pokemonListCopy = _listScreenState.pokemonList.sortedBy { it.name },
                            sortingToggle = newval)
                    } else {
                        if (filterByStartingStringMatch.isNotEmpty()) {
                            val copyList = filterByStartingStringMatch.toMutableList()
                            for(pokemon in filterByRelevance){
                                if(!copyList.contains(pokemon)){
                                    copyList.add(pokemon)
                                }
                            }
                            _listScreenState = _listScreenState.copy(
                                pokemonListCopy = copyList.sortedBy { it.name }.toList(),
                                sortingToggle = newval
                            )
                        } else{

                            _listScreenState = _listScreenState.copy(
                                pokemonListCopy =  filterByRelevance.sortedBy { it.name }.toList(),
                                sortingToggle = newval
                            )
                        }
                    }

                }
                else if(_listScreenState.sortingToggle==false){
                    if (_listScreenState.searchQueryValue == "") {

                        _listScreenState = _listScreenState.copy(pokemonListCopy = _listScreenState.pokemonList.sortedByDescending { it.name },
                            sortingToggle = newval)
                    } else {
                        if (filterByStartingStringMatch.isNotEmpty()) {
                            val copyList = filterByStartingStringMatch.toMutableList()
                            for(pokemon in filterByRelevance){
                                if(!copyList.contains(pokemon)){
                                    copyList.add(pokemon)
                                }
                            }
                            _listScreenState = _listScreenState.copy(
                                pokemonListCopy = copyList.sortedByDescending { it.name }.toList(),
                                sortingToggle = newval
                            )
                        } else{

                            _listScreenState = _listScreenState.copy(
                                pokemonListCopy =  filterByRelevance.sortedByDescending { it.name }.toList(),
                                sortingToggle = newval
                            )
                        }
                    }
                }
//                withContext(Dispatchers.Main){
//
//                    _listScreenState = _listScreenState.copy(sortingToggle = newval)
//                }

            }
        }

        override fun revertCardTapped() {
            viewModelScope.launch(Dispatchers.Main){
                delay(900)
                cardTapped = false
            }
        }


    }
}