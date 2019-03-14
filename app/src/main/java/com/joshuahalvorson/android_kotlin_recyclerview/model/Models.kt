package com.joshuahalvorson.android_kotlin_recyclerview.model

import kotlinx.serialization.Serializable

@Serializable
data class CardsList(val cards: List<Card>?)

@Serializable
data class Card(
    val name: String? = "",
    val manaCost: String? = "",
    val cmc: String? = "",
    val colors: List<String>? = listOf(String()),
    val colorIdentity: List<String>? = listOf(String()),
    val type: String? = "",
    val supertypes: List<Any>?,
    val types: List<String>? = listOf(String()),
    val subtypes: List<String>? = listOf(String()),
    val rarity: String? = "",
    val set: String? = "",
    val setName: String? = "",
    //val text: String? = "",
    val flavor: String? = "",
    val artist: String? = "",
    val number: String? = "",
    val power: String? = "",
    val toughness: String? = "",
    val layout: String? = "",
    val multiverseid: Int? = -1,
    val imageUrl: String? = "",
    //val variations: List<String>? = listOf(String()),
    val rulings: List<Any>? =  listOf(Any()),
    val foreignNames: List<ForeignName>? = listOf(ForeignName()),
    val printings: List<String>? = listOf(String()),
    //val originalText: String? = "",
    val originalType: String? = "",
    val legalities: List<Legalities>? = listOf(Legalities()),
    val id: String? = ""
)

@Serializable
data class ForeignName(
    val name: String? = "",
    val text: String? = "",
    val flavor: String? = "",
    val imageUrl: String? = "",
    val language: String? = "",
    val multiverseid: Int? = -1
)

@Serializable
data class Legalities(val format: String? = "", val legality: String? = "")