package com.ozturksahinyetisir.pokedex.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import com.ozturksahinyetisir.pokedex.R
import com.ozturksahinyetisir.pokedex.databinding.FragmentAllPokemonsBinding
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.RequestPaginate
import com.ozturksahinyetisir.pokedex.presentation.ui.adapters.AllPokemonsAdapter
import com.ozturksahinyetisir.pokedex.presentation.viewmodels.PokedexViewModel
import com.ozturksahinyetisir.pokedex.utils.CurrentList.currentList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class AllPokemonsFragment : Fragment() , AllPokemonsAdapter.OnPokemonSelected {

    private lateinit var binding: FragmentAllPokemonsBinding
    private lateinit var adapter: AllPokemonsAdapter
    private lateinit var pokemonViewModel: PokedexViewModel
    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPokemonsBinding.inflate(layoutInflater)
        pokemonViewModel = ViewModelProvider(this)[PokedexViewModel::class.java]
        adapter = context?.let { it.applicationContext?.let { it1 -> AllPokemonsAdapter(it1,this) } }!!
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.off_white)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        var isNameSearch = true

        shimmer = binding.shimmerViewContainer
        val builder = Shimmer.AlphaHighlightBuilder()
        builder.setDirection(Shimmer.Direction.TOP_TO_BOTTOM)
        shimmer.setShimmer(builder.build())
        binding.searchView.queryHint = "Search by Name"
        if(currentList.isEmpty()) getAllPokemon()
        else{
            binding.dataHolder.visibility = View.VISIBLE
            binding.shimmerViewContainer.visibility = View.GONE
            adapter.submitList(currentList.map { it.copy() })
        }

        binding.filterToggle.setOnClickListener {
            if (isNameSearch) {
                binding.searchView.queryHint = "Search by ID"
                binding.filterToggle.setImageResource(R.drawable.number)
                binding.searchView.setQuery("", false)
                isNameSearch = false
            } else {
                binding.searchView.queryHint = "Search by Name"
                binding.filterToggle.setImageResource(R.drawable.text)
                binding.searchView.setQuery("", false)
                isNameSearch = true
            }
        }

        binding.pokemonList.adapter = adapter


        binding.refresh.setOnRefreshListener {
            currentList.clear()
            getAllPokemon()
            binding.pokemonList.scrollToPosition(0)
        }

         fun filterList(query: String?) {
            if (query.isNullOrEmpty()) {
                adapter.submitList(currentList.toList())
            } else {
                val newList = if (isNameSearch) {
                    currentList.filter { pokemon ->
                        pokemon.name.contains(query, ignoreCase = true)
                    }
                } else {
                    currentList.filter { pokemon ->
                        pokemon.id.toString().contains(query, ignoreCase = true)
                    }
                }
                adapter.submitList(newList)
            }
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        shimmer.startShimmer()
    }
    override fun onPokemonClicked(item: PokemonDetailsModel , position: Int) {
        val fragment = PokemonDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("Pokemon", item)
        fragment.arguments = bundle
        transferTo(fragment)
    }


    private fun getAllPokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = pokemonViewModel.getAllPokemon(RequestPaginate.offset, RequestPaginate.limit)
                val list = ArrayList<PokemonDetailsModel>()
                for (item in response?.results ?: ArrayList()) {
                    pokemonViewModel.getPokemonByName(item.name)?.let { list.add(it) }
                }
                currentList.addAll(list)
                withContext(Dispatchers.Main) {
                    adapter.submitList(list)
                    shimmer.startShimmer()
                    binding.dataHolder.visibility = View.VISIBLE
                    binding.shimmerViewContainer.visibility = View.GONE
                    binding.refresh.isRefreshing = false
                }
            } catch (e: Exception) {
                e.toString()
                withContext(Dispatchers.Main){
                    Snackbar.make(requireView(), "Something Went Wrong", Snackbar.LENGTH_SHORT).show()
                    binding.refresh.isRefreshing = false
                }
            }
        }
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