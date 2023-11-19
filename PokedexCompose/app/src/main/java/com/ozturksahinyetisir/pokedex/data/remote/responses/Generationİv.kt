package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Generationİv(
    @SerializedName("diamond-pearl")
    val diamondPearl: com.ozturksahinyetisir.pokedex.data.remote.responses.DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: com.ozturksahinyetisir.pokedex.data.remote.responses.HeartgoldSoulsilver,
    @SerializedName("platinum")
    val platinum: com.ozturksahinyetisir.pokedex.data.remote.responses.Platinum
)