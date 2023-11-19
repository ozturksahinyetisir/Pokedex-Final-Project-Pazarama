package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: com.ozturksahinyetisir.pokedex.data.remote.responses.StatX
)