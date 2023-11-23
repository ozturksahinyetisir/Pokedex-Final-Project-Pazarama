package com.ozturksahinyetisir.pokedex.ui.detailscreen.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ozturksahinyetisir.pokedex.data.remote.responses.Pokemon
import com.ozturksahinyetisir.pokedex.data.remote.responses.Species
import com.ozturksahinyetisir.pokedex.data.remote.responses.Sprites
import com.ozturksahinyetisir.pokedex.network.PokemonRepository
import com.ozturksahinyetisir.pokedex.presentation.InfoScreen.PokemonInfoViewModel
import com.ozturksahinyetisir.pokedex.utils.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class InfoViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: PokemonRepository
    private lateinit var viewModel: PokemonInfoViewModel

    @Before
    fun setUp() {
        repository = Mockito.mock(PokemonRepository::class.java)
        viewModel = PokemonInfoViewModel(repository)
    }

    @Test
    fun getPokemonDetails() = runTest {
        val name = "Bulbasaur"

        Mockito.`when`(viewModel.getPokemonInfo(name)).thenReturn(getMockResponse())
        val result = viewModel.getPokemonInfo(name)

        Assert.assertEquals(getMockResponse().data, result.data)
    }

    private fun getMockResponse(): Resource<Pokemon> {
        return Resource.Success(getBulbasaur())
    }

    private fun getBulbasaur(): Pokemon {
        return Pokemon(7, 1, "Bulbasaur", Species("",""),Sprites(""), emptyList(), emptyList(), 69)
    }
}