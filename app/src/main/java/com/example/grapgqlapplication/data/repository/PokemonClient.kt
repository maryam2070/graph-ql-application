package com.example.grapgqlapplication.data.repository

import com.example.grapgqlapplication.data.model.Pokemon
import com.example.grapgqlapplication.data.model.PokemonItem
import com.example.grapgqlapplication.utils.Response
import kotlinx.coroutines.flow.Flow

interface PokemonClient {
    suspend fun getPokemons(): Flow<Response<List<PokemonItem>>>
    suspend fun getPokemon(name:String): Flow<Response<Pokemon>>
}