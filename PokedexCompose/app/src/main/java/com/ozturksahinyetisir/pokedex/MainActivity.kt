package com.ozturksahinyetisir.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ozturksahinyetisir.pokedex.navigation.NavigationContainer
import com.ozturksahinyetisir.pokedex.network.PokemonRepository
import com.ozturksahinyetisir.pokedex.presentation.InfoScreen.PokemonInfoViewModel
import com.ozturksahinyetisir.pokedex.presentation.mainScreen.PokemonMainViewModel
import com.ozturksahinyetisir.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var pRepository : PokemonRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pMainViewModel: PokemonMainViewModel by viewModels()
        val pInfoViewModel: PokemonInfoViewModel by viewModels()

        setContent {
            PokedexTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationContainer(
                        pMainViewModel,
                        pInfoViewModel
                    )
                }
            }
        }
    }
}