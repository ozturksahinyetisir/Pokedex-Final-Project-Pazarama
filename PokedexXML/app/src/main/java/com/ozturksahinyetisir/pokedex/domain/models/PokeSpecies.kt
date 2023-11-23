package com.ozturksahinyetisir.pokedex.domain.models

data class PokeSpecies(
    val color: Color,
    val id: Int,
    val flavor_text_entries: List<FlavorTextEntry>,
    val order: Int,
)