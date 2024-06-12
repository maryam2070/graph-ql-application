package com.example.grapgqlapplication.presentation

import com.example.grapgqlapplication.data.model.Pokemon
import com.example.grapgqlapplication.data.model.PokemonItem

data class UiState(
    val loading:Boolean=true,
    val error :String ="",
    val pokemons:List<PokemonItem> = emptyList(),
    val pokemon:Pokemon?=null
)