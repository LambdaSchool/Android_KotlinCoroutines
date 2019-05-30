package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Number(
    val number: String? = "",
    val fact: Int? = -1
)