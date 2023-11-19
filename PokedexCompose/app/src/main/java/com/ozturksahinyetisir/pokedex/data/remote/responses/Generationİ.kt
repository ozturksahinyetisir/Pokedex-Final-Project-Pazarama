package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Generationİ(
    @SerializedName("red-blue")
    val redBlue: com.ozturksahinyetisir.pokedex.data.remote.responses.RedBlue,
    @SerializedName("yellow")
    val yellow: com.ozturksahinyetisir.pokedex.data.remote.responses.Yellow
)