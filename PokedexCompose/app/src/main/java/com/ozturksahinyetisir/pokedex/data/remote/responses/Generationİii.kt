package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Generationİii(
    @SerializedName("emerald")
    val emerald: com.ozturksahinyetisir.pokedex.data.remote.responses.Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: com.ozturksahinyetisir.pokedex.data.remote.responses.FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: com.ozturksahinyetisir.pokedex.data.remote.responses.RubySapphire
)