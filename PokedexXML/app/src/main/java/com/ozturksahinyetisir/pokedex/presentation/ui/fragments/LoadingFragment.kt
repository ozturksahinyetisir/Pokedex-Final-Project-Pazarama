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
import com.ozturksahinyetisir.pokedex.R
import com.ozturksahinyetisir.pokedex.databinding.FragmentLoadingBinding
import com.ozturksahinyetisir.pokedex.presentation.viewmodels.PokedexViewModel
import dagger.hilt.android.AndroidEntryPoint


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

    //TODO get all pokemon here and save to Room so that we can use it offline

    private fun transferTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.nav_container, fragment)
        }
    }

}