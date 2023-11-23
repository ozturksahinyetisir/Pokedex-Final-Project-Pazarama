package com.ozturksahinyetisir.pokedex.data.remote

import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel

class RemoteDataSourceImpl(private val api : PokedexApi) : RemoteDataSource {
    override suspend fun getAllPokemon(offset:Int, limit:Int): PokemonModel? {
        return api.getAllPokemon(offset,limit).body()
    }

    override suspend fun getPokemonByName(name: String) : PokemonDetailsModel?{
        return api.getPokemonByName(name).body()
    }

    override suspend fun getSpeciesById(id: Int): PokeSpecies? {
        return api.getSpeciesByName(id).body()
    }
}