package com.ozturksahinyetisir.pokedex.data.remote

import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel

interface RemoteDataSource {
    suspend fun getAllPokemon(offset:Int, limit:Int) : PokemonModel?
    suspend fun getPokemonByName(name:String): PokemonDetailsModel?
    suspend fun getSpeciesById(id:Int): PokeSpecies?
}