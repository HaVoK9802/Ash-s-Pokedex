package com.example.ashspokedex.presentation.list

interface ListScreenUIEvents {
  fun liveSearch(updatedPokemonQuery: String)
  fun clearSearch()
  fun sortingFeature(newVal:Boolean)
  fun revertCardTapped()

  fun setCardTappedToTrue(cardTappedValue:Boolean)

//  fun returnUpdatedQuery(query:String)

}