package com.ozturksahinyetisir.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("abilities")
    val abilities: List<com.ozturksahinyetisir.pokedex.data.remote.responses.Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("forms")
    val forms: List<com.ozturksahinyetisir.pokedex.data.remote.responses.Form>,
    @SerializedName("game_indices")
    val gameİndices: List<com.ozturksahinyetisir.pokedex.data.remote.responses.Gameİndice>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("held_items")
    val heldİtems: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("moves")
    val moves: List<com.ozturksahinyetisir.pokedex.data.remote.responses.Move>,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any>,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    @SerializedName("species")
    val species: com.ozturksahinyetisir.pokedex.data.remote.responses.Species,
    @SerializedName("sprites")
    val sprites: com.ozturksahinyetisir.pokedex.data.remote.responses.Sprites,
    @SerializedName("stats")
    val stats: List<com.ozturksahinyetisir.pokedex.data.remote.responses.Stat>,
    @SerializedName("types")
    val types: List<com.ozturksahinyetisir.pokedex.data.remote.responses.Type>,
    @SerializedName("weight")
    val weight: Int
)