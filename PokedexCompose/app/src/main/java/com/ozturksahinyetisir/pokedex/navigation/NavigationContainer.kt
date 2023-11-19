package com.ozturksahinyetisir.pokedex.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ozturksahinyetisir.pokedex.data.remote.responses.Type
import com.ozturksahinyetisir.pokedex.presentation.InfoScreen.InfoScreen
import com.ozturksahinyetisir.pokedex.presentation.InfoScreen.PokemonInfoViewModel
import com.ozturksahinyetisir.pokedex.presentation.LoadingScreen
import com.ozturksahinyetisir.pokedex.presentation.mainScreen.MainScreen
import com.ozturksahinyetisir.pokedex.presentation.mainScreen.PokemonMainViewModel
import java.util.Locale


@Composable
fun NavigationContainer(
                        pViewModel: PokemonMainViewModel,
                        pInfoViewModel: PokemonInfoViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen" ){
        composable("loading_screen"){
            LoadingScreen(navController)
        }
        composable("main_screen"){
            MainScreen(navController,pViewModel)
        }
        composable("info_screen/{dominantColor}/{pokemonName}",arguments = listOf(
            navArgument("dominantColor") {
                type = NavType.IntType
            },
            navArgument("pokemonName") {
                type = NavType.StringType
            }
        )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            InfoScreen(dominantColor = dominantColor,
                pokemonName = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                navController,
                pInfoViewModel)
        }
    }
}