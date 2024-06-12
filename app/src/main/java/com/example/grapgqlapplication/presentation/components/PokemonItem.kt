package com.example.grapgqlapplication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.grapgqlapplication.data.model.PokemonItem
import com.example.grapgqlapplication.presentation.PokemonIntent
import com.example.grapgqlapplication.presentation.PokemonViewModel

@Composable
fun PokemonItem(pokemon: PokemonItem, viewModel: PokemonViewModel = hiltViewModel()) {

    Card(modifier = Modifier
        .padding(5.dp)
        .clickable {
            viewModel.handleIntent(PokemonIntent.GetPokemon(pokemon.name))
        }) {
        Row(Modifier.padding(5.dp), horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = pokemon.image,
                contentDescription = null,
            )
            Text(pokemon.name)
        }
    }
}