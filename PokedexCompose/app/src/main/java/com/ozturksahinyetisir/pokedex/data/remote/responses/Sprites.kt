package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String,
)