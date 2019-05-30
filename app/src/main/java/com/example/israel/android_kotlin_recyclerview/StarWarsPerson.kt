package com.example.israel.android_kotlin_recyclerview

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class StarWarsPerson(@Optional @SerialName("name") var name: String? = null) {

}