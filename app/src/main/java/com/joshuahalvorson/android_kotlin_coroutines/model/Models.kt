package com.joshuahalvorson.android_kotlin_coroutines.model

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class CardsList(val cards: List<Card>?)

@Serializable
data class Card(
    val name: String? = "",
    @Optional
    val manaCost: String? = "",
    @Optional
    val cmc: String? = "",
    @Optional
    val colors: List<String>? = listOf(String()),
    @Optional
    val colorIdentity: List<String>? = listOf(String()),
    @Optional
    val type: String? = "",
    val supertypes: List<Any>?,
    @Optional
    val types: List<String>? = listOf(String()),
    @Optional
    val subtypes: List<String>? = listOf(String()),
    @Optional
    val rarity: String? = "",
    @Optional
    val set: String? = "",
    @Optional
    val setName: String? = "",
    @Optional
    val text: String? = "",
    @Optional
    val flavor: String? = "",
    @Optional
    val artist: String? = "",
    @Optional
    val number: String? = "",
    @Optional
    val power: String? = "",
    @Optional
    val toughness: String? = "",
    @Optional
    val layout: String? = "",
    @Optional
    val multiverseid: Int? = -1,
    @Optional
    val imageUrl: String? = "",
    @Optional
    val variations: List<String>? = listOf(String()),
    @Optional
    val rulings: List<Any>? =  listOf(Any()),
    @Optional
    val foreignNames: List<ForeignName>? = listOf(ForeignName()),
    @Optional
    val printings: List<String>? = listOf(String()),
    @Optional
    val originalText: String? = "",
    @Optional
    val originalType: String? = "",
    @Optional
    val legalities: List<Legalities>? = listOf(Legalities()),
    @Optional
    val id: String? = ""
)

@Serializable
data class ForeignName(
    @Optional
    val name: String? = "",
    @Optional
    val text: String? = "",
    @Optional
    val flavor: String? = "",
    @Optional
    val imageUrl: String? = "",
    @Optional
    val language: String? = "",
    @Optional
    val multiverseid: Int? = -1
)

@Serializable
data class Legalities(
    @Optional
    val format: String? = "",
    @Optional
    val legality: String? = ""
)