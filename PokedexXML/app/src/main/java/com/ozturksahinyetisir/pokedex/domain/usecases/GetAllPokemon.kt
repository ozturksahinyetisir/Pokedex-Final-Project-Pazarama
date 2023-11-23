package com.ozturksahinyetisir.pokedex.domain.usecases

import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import com.ozturksahinyetisir.pokedex.domain.repository.PokedexRepository
import javax.inject.Inject

class GetAllPokemon @Inject constructor(private val repository: PokedexRepository) {
    suspend fun execute(offset:Int, limit:Int):PokemonModel?{
        return repository.getPokemonData(offset, limit)
    }
}