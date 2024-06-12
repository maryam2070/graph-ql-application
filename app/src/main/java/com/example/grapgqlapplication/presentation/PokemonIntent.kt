package com.example.grapgqlapplication.presentation

sealed class PokemonIntent {
    data object GetPokemons : PokemonIntent()
    data class GetPokemon(val name: String) : PokemonIntent()
    data object ClearPokemon : PokemonIntent()
}