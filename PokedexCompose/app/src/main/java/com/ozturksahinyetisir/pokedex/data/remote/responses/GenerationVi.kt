package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: com.ozturksahinyetisir.pokedex.data.remote.responses.OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xY: com.ozturksahinyetisir.pokedex.data.remote.responses.XY
)