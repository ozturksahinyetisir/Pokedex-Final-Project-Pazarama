package com.ozturksahinyetisir.pokedex.utils

import androidx.recyclerview.widget.DiffUtil
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel

class AllPokemonsDiffUtils : DiffUtil.ItemCallback<PokemonDetailsModel>() {
    override fun areItemsTheSame(oldItem: PokemonDetailsModel, newItem: PokemonDetailsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonDetailsModel, newItem: PokemonDetailsModel): Boolean {
        return oldItem == newItem
    }
}