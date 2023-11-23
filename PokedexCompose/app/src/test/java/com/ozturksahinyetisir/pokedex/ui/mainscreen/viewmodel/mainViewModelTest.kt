package com.ozturksahinyetisir.pokedex.ui.mainscreen.viewmodel

import com.ozturksahinyetisir.pokedex.data.remote.responses.Pokemon
import com.ozturksahinyetisir.pokedex.data.remote.responses.Sprites
import com.ozturksahinyetisir.pokedex.presentation.mainScreen.PokemonMainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import com.ozturksahinyetisir.pokedex.data.remote.responses.Result
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ozturksahinyetisir.pokedex.data.remote.responses.Species
import com.ozturksahinyetisir.pokedex.network.PokemonRepository

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class mainViewModelTest {

    @get:Rule
var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: PokemonRepository
    private lateinit var viewModel: PokemonMainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = mock(PokemonRepository::class.java)
        viewModel = PokemonMainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getImageUrl() = runTest {
        val pkm = getBulbasaur()
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"

        Assert.assertEquals(url, getImageUrl(pkm.id.toString()))
    }

    @Test
    fun getPokedexNumber() = runTest {
        val result = Result(
            "bulbasaur",
            "https://pokeapi.co/api/v2/pokemon/1/"
        )

        Assert.assertEquals("1", getPokedexNumber(result))
    }

    private fun getBulbasaur(): Pokemon {
        return Pokemon(7, 1, "Bulbasaur", Species("",""), Sprites(""), emptyList(), emptyList(), 69)
    }

    private fun getPokedexNumber(entry: Result): String {
        return if (entry.url.endsWith("/")) {
            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            entry.url.takeLastWhile { it.isDigit() }
        }
    }

    private fun getImageUrl(number: String): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
    }
}
