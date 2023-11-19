package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability")
    val ability: com.ozturksahinyetisir.pokedex.data.remote.responses.AbilityX,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)