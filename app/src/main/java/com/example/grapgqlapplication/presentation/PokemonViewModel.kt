package com.example.grapgqlapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grapgqlapplication.data.repository.PokemonClient
import com.example.grapgqlapplication.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val client: PokemonClient) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun handleIntent(intent: PokemonIntent) {
        viewModelScope.launch {
            when (intent) {
                is PokemonIntent.GetPokemons -> {
                    getPokemons()

                }
                is PokemonIntent.GetPokemon -> {
                    getPokemon(intent.name)
                }

                PokemonIntent.ClearPokemon -> {
                    _uiState.update {
                        it.copy(pokemon = null)
                    }
                }
            }
        }
    }

    private fun getPokemons()=viewModelScope.launch(Dispatchers.IO) {
        client.getPokemons().collect{response->

            when(response){
                is Response.Error -> {
                    _uiState.update {
                        it.copy(error = response.message!!, loading = false)
                    }
                }
                is Response.Loading -> {
                    _uiState.update {
                        it.copy( loading = true)
                    }
                }
                is Response.Success -> {

                    response.data?.let { pokemons ->
                        _uiState.update {
                            it.copy(pokemons =pokemons, loading = false)
                        }
                    }
                }
            }
        }
    }

    private fun getPokemon(name: String) =viewModelScope.launch(Dispatchers.IO){
        client.getPokemon(name).collect{response->

            when(response){
                is Response.Error -> {
                    _uiState.update {
                        it.copy(error = response.message!!, loading = false)
                    }
                }
                is Response.Loading -> {
                    //
                }
                is Response.Success -> {

                    response.data?.let { pokemon ->
                        _uiState.update {
                            it.copy(pokemon =pokemon, loading = false)
                        }
                    }
                }
            }
        }
    }

}