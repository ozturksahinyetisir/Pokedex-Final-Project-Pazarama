package com.ozturksahinyetisir.pokedex.ui

import com.ozturksahinyetisir.pokedex.domain.models.Color
import com.ozturksahinyetisir.pokedex.domain.models.OfficialArtwork
import com.ozturksahinyetisir.pokedex.domain.models.Other
import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import com.ozturksahinyetisir.pokedex.domain.models.Species
import com.ozturksahinyetisir.pokedex.domain.models.Sprites
import com.ozturksahinyetisir.pokedex.domain.models.Stat
import com.ozturksahinyetisir.pokedex.domain.models.StatX
import com.ozturksahinyetisir.pokedex.domain.models.Type
import com.ozturksahinyetisir.pokedex.domain.models.TypeX

object FakeData {

    fun getPokemonModel(): PokemonModel {
        return PokemonModel(count = 1, next = "", previous = "", results = emptyList())
    }

    fun getPokemonDetailsModel(): PokemonDetailsModel {
        return PokemonDetailsModel(
            id = 1,
            name = "Bulbasaur",
            types = listOf(Type(1, TypeX("",""))),
            stats = listOf(Stat(1,1, StatX("",""))),
            color = 14,
            height = 1,
            order = 1,
            species = Species("",""),
            sprites = Sprites("", Other(officialArtwork = OfficialArtwork(""))),
            weight = 9,
            )

    }

    fun getPokeSpecies(): PokeSpecies {
        return PokeSpecies(
            flavor_text_entries = emptyList(),
            color = Color("",""),
            id = 1,
            order = 1,
        )
    }
}