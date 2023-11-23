package com.ozturksahinyetisir.pokedex.presentation.ui.fragments

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.ozturksahinyetisir.pokedex.R
import com.ozturksahinyetisir.pokedex.databinding.FragmentPokemonDetailsBinding
import com.ozturksahinyetisir.pokedex.domain.models.*
import com.ozturksahinyetisir.pokedex.presentation.ui.adapters.AllPokemonsAdapter
import com.ozturksahinyetisir.pokedex.presentation.ui.adapters.PokemonTypeAdapter
import com.ozturksahinyetisir.pokedex.presentation.viewmodels.PokedexViewModel
import com.ozturksahinyetisir.pokedex.utils.CurrentList
import com.ozturksahinyetisir.pokedex.utils.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(), AllPokemonsAdapter.OnPokemonSelected {

    private lateinit var binding: FragmentPokemonDetailsBinding
    private lateinit var adapter: PokemonTypeAdapter
    private lateinit var pokemonViewModel: PokedexViewModel
    private var currentList = CurrentList.currentList

    private var snackBar: Snackbar? = null

    private var pokemon:PokemonDetailsModel? = null
    private var species:PokeSpecies? = null
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsBinding.inflate(layoutInflater)

        pokemonViewModel = ViewModelProvider(this)[PokedexViewModel::class.java]
        pokemon = arguments?.getParcelable("Pokemon")


        if(pokemon != null) {
            requireActivity().window.statusBarColor = requireActivity().getColor(pokemon!!.color)
            requireActivity().window.decorView.systemUiVisibility = 0

            adapter = PokemonTypeAdapter(typeListOfPokemon(pokemon!!.types), 1)
            getSpeciesDetails(pokemon!!.id)
            bindBaseStat()
            binding.backBtn.setOnClickListener {
                snackBar?.dismiss()
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
            }
            binding.imgNext.setOnClickListener {
                showNextPokemon()
            }
            binding.imgPrevious.setOnClickListener {
                showPreviousPokemon()
            }
            binding.apply {
                imgNext.setColorFilter(ContextCompat.getColor(
                    requireContext(),
                    pokemon!!.color))
                imgPrevious.setColorFilter(ContextCompat.getColor(
                    requireContext(),
                    pokemon!!.color))
                aboutTv.setTextColor(ContextCompat.getColor(
                    requireContext(),
                    pokemon!!.color
                ))
                baseStatsTv.setTextColor(ContextCompat.getColor(
                    requireContext(),
                    pokemon!!.color
                ))
                background.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        pokemon!!.color
                    )
                )
                ImageLoader.loadImageIntoImageView600(
                    pokemon!!.sprites.other.officialArtwork.front_default,
                    pokemonImg
                )
                idTv.text = "#${pokemon!!.id}"
                pokemonName.text = pokemon!!.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }

                val colorMatrix = ColorMatrix()
                colorMatrix.setSaturation(1.7f)
                val filter = ColorMatrixColorFilter(colorMatrix)
                pokemonImg.colorFilter = filter
                typeRc.adapter = adapter
            }
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    snackBar?.dismiss()
                    requireActivity().supportFragmentManager.popBackStack()
                    requireActivity().supportFragmentManager.beginTransaction().remove(this@PokemonDetailsFragment).commitAllowingStateLoss()
                }
            })

        return binding.root
    }

    private fun typeListOfPokemon(types: List<Type>):ArrayList<String>{
        val list = ArrayList<String>()
        for (item in types){
            list.add(item.type.name)
        }

        return list
    }

    @SuppressLint("SetTextI18n")
    private fun bindBaseStat(){
        if(pokemon != null){
            val hp = getStat("hp")
            val atk = getStat("attack")
            val def = getStat("defense")
            val spAtk = getStat("special-attack")
            val spDef = getStat("special-defense")
            val speed = getStat("speed")

            binding.apply {
                bindStatView(hpTv, hpIndicator, hp)
                bindStatView(atkTv, atkIndicator, atk)
                bindStatView(defTv, defIndicator, def)
                bindStatView(spAttackTv, spAtkIndicator, spAtk)
                bindStatView(spDefTv, spDefIndicator, spDef)
                bindStatView(speedTv, speedIndicator, speed)
                heightTv.text = "${pokemon?.height?.toFloat()?.div(10)} m"
                weightTv.text = "${pokemon?.weight?.toFloat()?.div(10)} kg"
                totalTv.text = (hp.second+atk.second+def.second+spAtk.second+spDef.second+speed.second).toString()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getSpeciesDetails(id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                withContext(Dispatchers.IO){
                    species = pokemonViewModel.getPokeSpecies(id)!!
                }
                binding.infoTv.text = species?.flavor_text_entries?.let { getFlavourText(it) }

            }catch (e:Exception){
                snackBar = Snackbar.make(requireView(), "Something went Wrong", Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(android.graphics.Color.RED)
                    .setAction("Refresh") {
                        getSpeciesDetails(id)
                    }
                snackBar!!.show()
            }
        }
    }

    private fun bindStatView(textView: TextView, linearProgressIndicator: LinearProgressIndicator, pair :Pair<String,Int>){
        textView.text = pair.first
        linearProgressIndicator.progress = pair.second
        linearProgressIndicator.setIndicatorColor(requireActivity().getColor(pokemon!!.color))
    }

    private fun getStat(stateName:String):Pair<String,Int>{
        for (item in pokemon?.stats!!){
            if(item.stat.name == stateName){
                return Pair(item.base_stat.toString(),item.base_stat)
            }
        }
        return Pair("",0)
    }

    override fun onDestroy() {
        super.onDestroy()
        snackBar = null
    }

    private fun showPreviousPokemon() {
        val currentPosition = currentList.indexOf(pokemon)
        val previousPosition = if (currentPosition > 0) currentPosition - 1 else currentList.size - 1
        showPokemonAtPosition(previousPosition)
    }

    private fun showNextPokemon() {
        val currentPosition = currentList.indexOf(pokemon)
        val nextPosition = if (currentPosition < currentList.size - 1) currentPosition + 1 else 0
        showPokemonAtPosition(nextPosition)
    }

    private fun showPokemonAtPosition(position: Int) {
        if (position in 0 until currentList.size) {
            val nextPokemon = currentList[position]
            val fragment = PokemonDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable("Pokemon", nextPokemon)
            fragment.arguments = bundle
            transferTo(fragment)
        }
    }
    override fun onPokemonClicked(item: PokemonDetailsModel, position: Int) {
        val fragment = PokemonDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("Pokemon", item)
        fragment.arguments = bundle
        transferTo(fragment)
    }
    private fun getFlavourText(list: List<FlavorTextEntry>):String{
        for (item in list){
            if(item.language.name == "en"){
                return item.flavor_text.replace("\n","")
            }
        }
        return ""
    }
    private fun transferTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            addToBackStack("frag")
            setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_bottom,
                androidx.appcompat.R.anim.abc_fade_out,
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_slide_out_bottom
            )
            replace(R.id.nav_container, fragment)
        }
    }

}

