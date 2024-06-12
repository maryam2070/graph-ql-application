package com.example.grapgqlapplication.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.grapgqlapplication.presentation.UiState

@Composable
fun PokemonList(uiStat: State<UiState>) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (uiStat.value.loading) {
            Text("Loading")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(uiStat.value.pokemons) {
                    PokemonItem(it)
                }
            }
        }
    }
}