package com.ozturksahinyetisir.pokedex.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozturksahinyetisir.pokedex.databinding.TypeItemBinding
import com.ozturksahinyetisir.pokedex.databinding.TypeItemPokemonDetailsBinding

class PokemonTypeAdapter(private val list:ArrayList<String> , private val viewNum: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TypeViewHolder(val binding: TypeItemBinding) : RecyclerView.ViewHolder(binding.root)
    class TypePokemonDetailsViewHolder(val binding: TypeItemPokemonDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewNum == 0) {
            return TypeViewHolder(
                TypeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else{
            return TypePokemonDetailsViewHolder(
                TypeItemPokemonDetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is TypeViewHolder) {
            holder.binding.pokemonType.text = list[holder.adapterPosition]
        }else if (holder is TypePokemonDetailsViewHolder){
            holder.binding.pokemonType.text = list[holder.adapterPosition]
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}