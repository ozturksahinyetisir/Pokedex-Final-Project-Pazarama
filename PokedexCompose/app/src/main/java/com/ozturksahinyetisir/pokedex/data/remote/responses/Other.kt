package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: com.ozturksahinyetisir.pokedex.data.remote.responses.DreamWorld,
    @SerializedName("home")
    val home: com.ozturksahinyetisir.pokedex.data.remote.responses.Home,
    @SerializedName("official-artwork")
    val officialArtwork: com.ozturksahinyetisir.pokedex.data.remote.responses.OfficialArtwork
)