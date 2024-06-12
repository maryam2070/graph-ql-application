package com.example.grapgqlapplication.presentation

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.grapgqlapplication.data.model.Pokemon
import com.example.grapgqlapplication.data.model.PokemonItem
import com.example.grapgqlapplication.data.repository.PokemonClient
import com.example.grapgqlapplication.presentation.components.PokemonDialog
import com.example.grapgqlapplication.presentation.components.PokemonList
import com.example.grapgqlapplication.ui.theme.GrapgQlApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var pokemonClient: PokemonClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrapgQlApplicationTheme {
                val viewModel: PokemonViewModel = hiltViewModel()

                LaunchedEffect(true) {
                    viewModel.handleIntent(PokemonIntent.GetPokemons)
                }

                if(viewModel.uiState.collectAsState().value.error!=""){
                    Toast.makeText(LocalContext.current,viewModel.uiState.collectAsState().value.error,Toast.LENGTH_SHORT).show()
                }
                if(viewModel.uiState.collectAsState().value.pokemon!=null){
                    PokemonDialog(pokemon = viewModel.uiState.collectAsState().value.pokemon!!) {intent->
                        viewModel.handleIntent(intent)
                    }
                }

                Scaffold {paddingValues ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)) {

                        Text(
                            "Pokemons",
                            Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            textAlign = TextAlign.Center
                        )
                        PokemonList(viewModel.uiState.collectAsState())
                    }
                }
            }
        }
    }
}









