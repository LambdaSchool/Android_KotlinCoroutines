package com.jbseppanen.android_kotlin_recyclerview

import kotlinx.serialization.Serializable

@Serializable
data class Diversion(
    val accessibility: Double? = 0.0,
    val activity: String? = "",
    val key: String? = "",
//    val link: String? = "",  //TODO handle the link when not present.
    val participants: Int? = 0,
    val price: Double? = 0.0,
    val type: String? = ""
)
