package com.ozturksahinyetisir.pokedex.presentation.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturksahinyetisir.pokedex.data.models.PokedexModel
import com.ozturksahinyetisir.pokedex.network.PokemonRepository
import com.ozturksahinyetisir.pokedex.utils.Constants.PAGE_SIZE
import com.ozturksahinyetisir.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonMainViewModel @Inject constructor(private val
    pRepository:PokemonRepository
): ViewModel(){

    private var curPage = 0

    var pokemonList = mutableStateOf<List<PokedexModel>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)
    var iconStateValue = true

    private var cachedPokemonList = listOf<PokedexModel>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if(isSearchStarting) {
            pokemonList.value
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            if(iconStateValue == true){
                val results = listToSearch.filter {
                    it.pokemonName.contains(query.trim(), ignoreCase = true)
                }
                if(isSearchStarting) {
                    cachedPokemonList = pokemonList.value
                    isSearchStarting = false
                }
                pokemonList.value = results
                isSearching.value = true
            }else{
                val results = listToSearch.filter {
                    it.number.toString() == query.trim()
                }
                if(isSearchStarting) {
                    cachedPokemonList = pokemonList.value
                    isSearchStarting = false
                }
                pokemonList.value = results
                isSearching.value = true
            }

        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = pRepository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexModel(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value = pokemonList.value + pokedexEntries
                }

                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                else -> {}
            }
        }
    }
}
