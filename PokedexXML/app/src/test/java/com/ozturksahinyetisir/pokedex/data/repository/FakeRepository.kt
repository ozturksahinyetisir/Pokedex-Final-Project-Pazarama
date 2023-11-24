package com.ozturksahinyetisir.pokedex.data.repository

import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import com.ozturksahinyetisir.pokedex.domain.repository.PokedexRepository
import com.ozturksahinyetisir.pokedex.ui.FakeData

class FakeRepository : PokedexRepository {

    override suspend fun getPokemonData(offset: Int, limit: Int): PokemonModel? {
        return FakeData.getPokemonModel()
    }

    override suspend fun getPokemonByName(name: String): PokemonDetailsModel? {
        return FakeData.getPokemonDetailsModel()
    }

    override suspend fun getSpeciesByID(id: Int): PokeSpecies? {
        return FakeData.getPokeSpecies()
    }
}