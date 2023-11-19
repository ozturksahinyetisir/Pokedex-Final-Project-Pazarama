package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: com.ozturksahinyetisir.pokedex.data.remote.responses.MoveX,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<com.ozturksahinyetisir.pokedex.data.remote.responses.VersionGroupDetail>
)