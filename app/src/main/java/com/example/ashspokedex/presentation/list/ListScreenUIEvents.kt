package com.example.ashspokedex.presentation.list

interface ListScreenUIEvents {
  fun onSearch(updatedPokemonQuery: String)
  fun clearSearchResult()
  fun sortAlphabetically()
  fun cardTapped()
}