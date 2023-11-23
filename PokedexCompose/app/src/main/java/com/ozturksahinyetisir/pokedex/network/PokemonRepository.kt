package com.ozturksahinyetisir.pokedex.network

import com.ozturksahinyetisir.pokedex.data.remote.responses.Pokemon
import com.ozturksahinyetisir.pokedex.data.remote.responses.PokemonList
import com.ozturksahinyetisir.pokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(private val service : PokemonService){


    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return try {
            val response = service.getPokemonList(limit, offset)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Bir hata oluştu.")

        }
    }
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
    return try {
        val response = service.getPokemonInfo(pokemonName)
        Resource.Success(response)
    } catch (e: Exception) {
        Resource.Error("Bir hata oluştu.")
    }
}

}


