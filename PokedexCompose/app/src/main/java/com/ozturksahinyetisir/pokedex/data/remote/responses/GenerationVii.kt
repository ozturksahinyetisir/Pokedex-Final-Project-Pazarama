package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("icons")
    val icons: com.ozturksahinyetisir.pokedex.data.remote.responses.İcons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: com.ozturksahinyetisir.pokedex.data.remote.responses.UltraSunUltraMoon
)