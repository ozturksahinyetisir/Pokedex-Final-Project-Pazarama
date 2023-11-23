package com.ozturksahinyetisir.pokedex.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozturksahinyetisir.pokedex.R
import com.ozturksahinyetisir.pokedex.domain.models.Type
import com.ozturksahinyetisir.pokedex.utils.ImageLoader
import com.google.android.material.card.MaterialCardView
import com.ozturksahinyetisir.pokedex.databinding.PokemonItemBinding
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.utils.AllPokemonsDiffUtils
import java.util.*
import kotlin.collections.ArrayList


class AllPokemonsAdapter(val context: Context, private val onPokemonSelected: OnPokemonSelected) : ListAdapter<PokemonDetailsModel, AllPokemonsAdapter.PokemonViewHolder>(
    AllPokemonsDiffUtils()
){

    private lateinit var adapter:PokemonTypeAdapter

    class PokemonViewHolder(val binding: PokemonItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        adapter = PokemonTypeAdapter(typeListOfPokemon(item.types),0)
        holder.binding.apply {
            numberTv.text = "#${item.id}"
            pokemonName.text = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            ImageLoader.loadImageIntoImageView(item.sprites.other.officialArtwork.front_default,pokemonImg)
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(1.7f)
            val filter = ColorMatrixColorFilter(colorMatrix)
            pokemonImg.colorFilter = filter
            colorizeView(pokemonCard, item)
        }

        holder.itemView.findViewById<MaterialCardView>(R.id.pokemon_card).setOnClickListener{
            onPokemonSelected.onPokemonClicked(item,holder.adapterPosition)
        }
    }


    private fun typeListOfPokemon(types: List<Type>):ArrayList<String>{
        val list = ArrayList<String>()
        for (item in types){

            list.add(item.type.name)
        }

        return list
    }

    @SuppressLint("ResourceAsColor")
    private fun colorizeView(view: MaterialCardView, item:PokemonDetailsModel){
        item.color = when (item.types[0].type.name.lowercase()) {
            "fire" -> R.color.red
            "grass" -> R.color.light_green
            "bug" -> R.color.brown
            "ground" ->  R.color.brown
            "water" -> R.color.cyan
            "electric" -> R.color.orange
            "fairy" -> R.color.pink
            "poison" -> R.color.purple
            else -> R.color.grey
        }
        view.setCardBackgroundColor(getColor(context, item.color))
    }


    interface OnPokemonSelected {
        fun onPokemonClicked(item : PokemonDetailsModel,position: Int)
    }
}