package com.ozturksahinyetisir.pokedex.data.repository

import com.ozturksahinyetisir.pokedex.data.remote.RemoteDataSource
import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import com.ozturksahinyetisir.pokedex.domain.repository.PokedexRepository
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : PokedexRepository {
    override suspend fun getPokemonData(offset: Int, limit: Int): PokemonModel? {
        return remoteDataSource.getAllPokemon(offset, limit)
    }

    override suspend fun getPokemonByName(name: String): PokemonDetailsModel? {
        return remoteDataSource.getPokemonByName(name)
    }

    override suspend fun getSpeciesByID(id: Int): PokeSpecies? {
        return remoteDataSource.getSpeciesById(id)
    }

}