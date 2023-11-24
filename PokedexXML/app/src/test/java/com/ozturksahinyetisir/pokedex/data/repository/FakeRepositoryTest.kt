package com.ozturksahinyetisir.pokedex.data.repository

import com.ozturksahinyetisir.pokedex.ui.FakeData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FakeRepositoryTest {

    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
    }

    @Test
    fun `getPokemonData fakeData`() = runBlocking {
        val fakeRepository = FakeRepository()
        val result = fakeRepository.getPokemonData(0, 10)

        Assert.assertEquals(FakeData.getPokemonModel(), result)
    }

    @Test
    fun `getPokemonByName fakeData`() = runBlocking {
        val fakeRepository = FakeRepository()
        val result = fakeRepository.getPokemonByName("Bulbasaur")

        Assert.assertEquals(FakeData.getPokemonDetailsModel(), result)
    }

    @Test
    fun `getSpeciesByID fakeData`() = runBlocking {
        val fakeRepository = FakeRepository()
        val result = fakeRepository.getSpeciesByID(1)

        Assert.assertEquals(FakeData.getPokeSpecies(), result)
    }
}