package com.example.israel.android_kotlin_recyclerview

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class StarWarsPeopleResult(@SerialName("results") var results: ArrayList<StarWarsPerson>?)