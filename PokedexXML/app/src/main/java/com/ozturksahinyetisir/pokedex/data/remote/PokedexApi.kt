package com.ozturksahinyetisir.pokedex.data.remote

import com.ozturksahinyetisir.pokedex.domain.models.PokeSpecies
import com.ozturksahinyetisir.pokedex.domain.models.PokemonDetailsModel
import com.ozturksahinyetisir.pokedex.domain.models.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    @GET("pokemon")
    suspend fun getAllPokemon(@Query("offset") offset:Int, @Query("limit") limit:Int) : Response<PokemonModel>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name:String):Response<PokemonDetailsModel>

    @GET("pokemon-species/{id}")
    suspend fun getSpeciesByName(@Path("id") id:Int):Response<PokeSpecies>
}