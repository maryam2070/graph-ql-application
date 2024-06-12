package com.example.grapgqlapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.grapgqlapplication.data.model.Pokemon
import com.example.grapgqlapplication.presentation.PokemonIntent

@Composable
fun PokemonDialog(pokemon: Pokemon, onItemClick: (PokemonIntent) -> Unit){

    Dialog(onDismissRequest = {
        onItemClick(PokemonIntent.ClearPokemon)
    },) {

        Card(modifier = Modifier.background(Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                Modifier.padding(10.dp),
            ) {

                Text(text = pokemon.name+" Details", modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold)

                DataList(pokemon.abilities,"Abilities")

                DataList(pokemon.moves,"Moves")

            }
        }
    }
}