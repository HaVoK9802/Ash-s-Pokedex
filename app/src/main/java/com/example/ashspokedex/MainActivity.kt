package com.example.ashspokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ashspokedex.presentation.detail.PokemonDetailScreen
import com.example.ashspokedex.presentation.detail.PokemonDetailViewModel
import com.example.ashspokedex.presentation.list.PokemonListScreen
import com.example.ashspokedex.presentation.list.PokemonListViewModel
import com.example.ashspokedex.ui.theme.AshsPokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AshsPokedexTheme {

                val navController = rememberNavController()
                val pokemonListViewModel:PokemonListViewModel = hiltViewModel()
                LaunchedEffect(Unit){
                    pokemonListViewModel.listScreenUiEvents.getPokemonList()
                }


                NavHost(
                    navController = navController ,
                    startDestination = "pokemon_list_screen"){

                    composable(
                        route = "pokemon_list_screen"
                    ){

                        PokemonListScreen(
//                            searchQuery = pokemonListViewModel.searchedQuery,
                            navController = navController,
                            pokemonlistScreenUIEvents = pokemonListViewModel.listScreenUiEvents,
                            pokemonListScreenStates = pokemonListViewModel._listScreenState,
                            cardTapped = pokemonListViewModel.cardTapped
                        )

                    }

                    composable(
                        route ="pokemon_detail_screen/{pokemonName}",
                        arguments = listOf(
                            navArgument(name = "pokemonName"){
                                type = NavType.StringType
                            }
                        )
                    ){
                        val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
                        val pokemonName = it.arguments?.getString("pokemonName")
                        LaunchedEffect(Unit) {
                            pokemonDetailViewModel.fetchPokemonStats(pokemonName?:"")
                        }
                        PokemonDetailScreen(
                            navController = navController ,
                            pokemonDetailScreenStates = pokemonDetailViewModel._detailScreenStates,
                            tapBackIcon = {pokemonDetailViewModel.tapBackIcon()},
                            backIconTapped = pokemonDetailViewModel.backIconTapped
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AshsPokedexTheme {
        Greeting("Android")
    }
}