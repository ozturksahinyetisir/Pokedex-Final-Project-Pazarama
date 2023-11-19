package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Generationİi(
    @SerializedName("crystal")
    val crystal: com.ozturksahinyetisir.pokedex.data.remote.responses.Crystal,
    @SerializedName("gold")
    val gold: com.ozturksahinyetisir.pokedex.data.remote.responses.Gold,
    @SerializedName("silver")
    val silver: com.ozturksahinyetisir.pokedex.data.remote.responses.Silver
)