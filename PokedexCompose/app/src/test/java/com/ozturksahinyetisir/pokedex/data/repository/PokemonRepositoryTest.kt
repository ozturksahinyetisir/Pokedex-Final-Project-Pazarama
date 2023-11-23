package com.ozturksahinyetisir.pokedex.data.repository

import com.ozturksahinyetisir.pokedex.data.remote.responses.Pokemon
import com.ozturksahinyetisir.pokedex.data.remote.responses.PokemonList
import com.ozturksahinyetisir.pokedex.data.remote.responses.Species
import com.ozturksahinyetisir.pokedex.data.remote.responses.Sprites
import com.ozturksahinyetisir.pokedex.network.PokemonRepository
import com.ozturksahinyetisir.pokedex.presentation.InfoScreen.PokemonInfoViewModel
import com.ozturksahinyetisir.pokedex.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class PokemonRepositoryTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()


    private lateinit var repository: PokemonRepository
    private lateinit var viewModel: PokemonInfoViewModel
    @Before
    fun setUp() {
        repository = Mockito.mock(PokemonRepository::class.java)
    }


    @Test
    fun `getPokemonInfo should return success`() = runTest {

        val pokemonName = "Bulbasaur"
        val expectedPokemonInfo = getPokemonInfo()

        Mockito.`when`(repository.getPokemonInfo(pokemonName)).thenReturn(Resource.Success(expectedPokemonInfo))

        val result = repository.getPokemonInfo(pokemonName)

        if (result is Resource.Success) {
            Assert.assertEquals(expectedPokemonInfo, result.data)
        } else {
            Assert.fail("Error: $result")
        }
    }

    @Test
    fun getPokemonList() = runTest {
        val expectedPokemonList = PokemonList(count = 1, next = "", previous = "", results = emptyList())

        Mockito.`when`(repository.getPokemonList(10, 0)).thenReturn(Resource.Success(expectedPokemonList))

        val result = repository.getPokemonList(10, 0)

        Assert.assertTrue(result is Resource.Success)
        Assert.assertEquals(expectedPokemonList, (result as Resource.Success).data)
    }

    private fun getPokemonInfo(): Pokemon {
        return Pokemon(7, 1, "Bulbasaur", Species("",""), Sprites(""), emptyList(), emptyList(), 69)
    }
}