package com.ozturksahinyetisir.pokedex.domain.repository

import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel


interface PokedexRepository {
    suspend fun getPokemonData(offset:Int, limit:Int): PokemonModel?
    suspend fun getPokemonByName(name:String): PokemonDetailsModel?
    suspend fun getSpeciesByID(id:Int):PokeSpecies?


}