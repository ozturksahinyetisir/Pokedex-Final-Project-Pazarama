package com.ozturksahinyetisir.pokedex.domain.usecases

import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.repository.PokedexRepository
import javax.inject.Inject

class GetPokeSpecies @Inject constructor(private val repository: PokedexRepository) {
    suspend fun execute(id:Int):PokeSpecies?{
        return repository.getSpeciesByID(id)
    }
}