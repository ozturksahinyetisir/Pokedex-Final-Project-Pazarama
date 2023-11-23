package com.ozturksahinyetisir.pokedex.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatX(
    val name: String,
    val url: String
):Parcelable