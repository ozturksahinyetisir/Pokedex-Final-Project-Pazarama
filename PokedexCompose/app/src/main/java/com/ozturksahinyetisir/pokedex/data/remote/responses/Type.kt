package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: com.ozturksahinyetisir.pokedex.data.remote.responses.TypeX
)