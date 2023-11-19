package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-v")
    val generationV: com.ozturksahinyetisir.pokedex.data.remote.responses.GenerationV,
    @SerializedName("generation-vi")
    val generationVi: com.ozturksahinyetisir.pokedex.data.remote.responses.GenerationVi,
    @SerializedName("generation-vii")
    val generationVii: com.ozturksahinyetisir.pokedex.data.remote.responses.GenerationVii,
    @SerializedName("generation-viii")
    val generationViii: com.ozturksahinyetisir.pokedex.data.remote.responses.GenerationViii,
    @SerializedName("generation-i")
    val generationİ: com.ozturksahinyetisir.pokedex.data.remote.responses.Generationİ,
    @SerializedName("generation-ii")
    val generationİi: com.ozturksahinyetisir.pokedex.data.remote.responses.Generationİi,
    @SerializedName("generation-iii")
    val generationİii: com.ozturksahinyetisir.pokedex.data.remote.responses.Generationİii,
    @SerializedName("generation-iv")
    val generationİv: com.ozturksahinyetisir.pokedex.data.remote.responses.Generationİv
)