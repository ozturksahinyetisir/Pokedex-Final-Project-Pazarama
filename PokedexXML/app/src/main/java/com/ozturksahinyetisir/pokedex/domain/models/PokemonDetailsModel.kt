package com.ozturksahinyetisir.pokedex.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailsModel(
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int,
    var color : Int
):Parcelable