package com.example.grapgqlapplication.data.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.example.GetPokemonQuery
import com.example.GetPokemonsQuery
import com.example.grapgqlapplication.data.model.Pokemon
import com.example.grapgqlapplication.data.model.PokemonItem
import com.example.grapgqlapplication.data.model.Type
import com.example.grapgqlapplication.utils.Constants.UNKNOWN_ERROR_OCCURRED
import com.example.grapgqlapplication.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApolloPokemonClient @Inject constructor(private val client: ApolloClient) : PokemonClient {
    override suspend fun getPokemons(): Flow<Response<List<PokemonItem>>> = flow {
        emit(Response.Loading())
        try {
            val pokemons = client.query(GetPokemonsQuery()).execute().data?.pokemons?.results?.map {
                PokemonItem(name = it?.name!!, image = it.image!!, url = it.url!!)
            }
            Log.d("ApolloPokemonClient", pokemons.toString())
            if (pokemons == null) {
                emit(Response.Error("No pokemons found"))
            } else {
                emit(Response.Success(pokemons))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: UNKNOWN_ERROR_OCCURRED))
        }
    }

    override suspend fun getPokemon(name: String): Flow<Response<Pokemon>> = flow {

        emit(Response.Loading())
        try {
            val pokemonResult = client.query(GetPokemonQuery(name)).execute().data?.pokemon!!
            val pokemon = Pokemon(
                name = pokemonResult.name!!,
                id = pokemonResult.id!!,
                types = pokemonResult.types?.map { Type(name = it?.type?.name!!) }!!,
                abilities = pokemonResult.abilities?.map { it?.ability?.name!! }!!,
                moves = pokemonResult.moves?.map { it?.move?.name!! }!!,
                status = pokemonResult.status!!,
                message = pokemonResult.message!!,
            )

            emit(Response.Success(pokemon))

        } catch (e: Exception) {
            emit(Response.Error(e.message ?: UNKNOWN_ERROR_OCCURRED))
        }

    }
}