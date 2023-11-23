package com.ozturksahinyetisir.pokedex.presentation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ozturksahinyetisir.pokedex.R
import com.ozturksahinyetisir.pokedex.databinding.FragmentLoadingBinding
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.RequestPaginate
import com.ozturksahinyetisir.pokedex.presentation.viewmodels.PokedexViewModel
import com.ozturksahinyetisir.pokedex.utils.CurrentList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class LoadingFragment : Fragment() {

    private lateinit var binding: FragmentLoadingBinding
    private lateinit var pokemonViewModel: PokedexViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(layoutInflater)
        pokemonViewModel = ViewModelProvider(this)[PokedexViewModel::class.java]
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.off_white)

        Handler(Looper.getMainLooper()).postDelayed({
            transferTo(AllPokemonsFragment())
        }, 1500L)
        return binding.root
    }

    private fun getAllPokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = pokemonViewModel.getAllPokemon(RequestPaginate.offset, RequestPaginate.limit)
                val list = ArrayList<PokemonDetailsModel>()
                for (item in response?.results ?: ArrayList()) {
                    pokemonViewModel.getPokemonByName(item.name)?.let { list.add(it) }
                }
                CurrentList.currentList.addAll(list)
                transferTo(AllPokemonsFragment())
            } catch (e: Exception) {
                e.toString()
                withContext(Dispatchers.Main){
                    Snackbar.make(requireView(), "Something Went Wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun transferTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.nav_container, fragment)
        }
    }

}