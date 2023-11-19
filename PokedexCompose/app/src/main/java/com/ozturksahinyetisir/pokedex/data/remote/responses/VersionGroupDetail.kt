package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("move_learn_method")
    val moveLearnMethod: com.ozturksahinyetisir.pokedex.data.remote.responses.MoveLearnMethod,
    @SerializedName("version_group")
    val versionGroup: com.ozturksahinyetisir.pokedex.data.remote.responses.VersionGroup
)