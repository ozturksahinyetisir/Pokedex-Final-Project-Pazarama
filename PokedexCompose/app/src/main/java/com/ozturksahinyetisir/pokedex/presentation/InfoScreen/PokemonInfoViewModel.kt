package com.ozturksahinyetisir.pokedex.presentation.InfoScreen

import androidx.lifecycle.ViewModel
import com.ozturksahinyetisir.pokedex.network.PokemonRepository
import com.ozturksahinyetisir.pokedex.data.remote.responses.Pokemon
import com.ozturksahinyetisir.pokedex.data.remote.responses.PokemonList
import com.ozturksahinyetisir.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val pRepository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return pRepository.getPokemonInfo(pokemonName)
    }

    suspend fun getPokemonList(pokemonName: String): Resource<PokemonList> {
        return pRepository.getPokemonList(20,20)
    }
}