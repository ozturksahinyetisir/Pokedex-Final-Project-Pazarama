package com.ozturksahinyetisir.pokedex.domain.usecases

import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.repository.PokedexRepository
import javax.inject.Inject

class GetPokemonByName @Inject constructor(private val repository: PokedexRepository) {
    suspend fun execute(name:String): PokemonDetailsModel?{
        return repository.getPokemonByName(name)
    }
}