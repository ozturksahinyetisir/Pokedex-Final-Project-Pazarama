package com.ozturksahinyetisir.pokedex.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Sprites(
    val front_default: String,
    val other: Other,
):Parcelable