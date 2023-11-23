package com.ozturksahinyetisir.pokedex.presentation.viewmodels
import androidx.lifecycle.ViewModel
import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import com.ozturksahinyetisir.pokedex.domain.usecases.GetAllPokemon
import com.ozturksahinyetisir.pokedex.domain.usecases.GetPokeSpecies
import com.ozturksahinyetisir.pokedex.domain.usecases.GetPokemonByName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getAllPokemon: GetAllPokemon,
    private val getPokemonByName: GetPokemonByName,
    private val getPokeSpecies: GetPokeSpecies
) : ViewModel() {

    suspend fun getAllPokemon(offset:Int, limit:Int):PokemonModel?{
        return getAllPokemon.execute(offset,limit)
    }

    suspend fun getPokemonByName(name:String): PokemonDetailsModel?{
        return getPokemonByName.execute(name)
    }

    suspend fun getPokeSpecies(id:Int):PokeSpecies?{
        return getPokeSpecies.execute(id)
    }
}