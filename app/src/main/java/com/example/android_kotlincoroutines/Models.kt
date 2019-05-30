package com.example.android_kotlincoroutines

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Diversion(
    val accessibility: Double? = 0.0,
    val activity: String? = "",
    val key: String? = "",
    @Optional val link: String? = "",
    val participants: Int? = 0,
    val price: Double? = 0.0,
    val type: String? = ""
)