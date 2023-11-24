package com.ozturksahinyetisir.pokedex.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import com.ozturksahinyetisir.pokedex.domain.usecases.GetAllPokemon
import com.ozturksahinyetisir.pokedex.domain.usecases.GetPokeSpecies
import com.ozturksahinyetisir.pokedex.domain.usecases.GetPokemonByName
import com.ozturksahinyetisir.pokedex.presentation.viewmodels.PokedexViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getAllPokemon: GetAllPokemon = mockk()
    private val getPokemonByName: GetPokemonByName = mockk()
    private val getPokeSpecies: GetPokeSpecies = mockk()

    private val viewModel = PokedexViewModel(getAllPokemon, getPokemonByName, getPokeSpecies)

    @Test
    fun `getAllPokemon test`() = runTest {
        val offset = 0
        val limit = 10
        val expectedResult = mockk<PokemonModel>()

        coEvery { getAllPokemon.execute(offset, limit) } returns expectedResult


        val result = viewModel.getAllPokemon(offset, limit)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `getPokemonByName test`() = runTest {

        val name = "Bulbasaur"
        val expectedResult = mockk<PokemonDetailsModel>()

        coEvery { getPokemonByName.execute(name) } returns expectedResult

        val result = viewModel.getPokemonByName(name)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `getPokeSpecies test`() = runTest {

        val id = 1
        val expectedResult = mockk<PokeSpecies>()

        coEvery { getPokeSpecies.execute(id) } returns expectedResult

        val result = viewModel.getPokeSpecies(id)

        Assert.assertEquals(expectedResult, result)
    }
}