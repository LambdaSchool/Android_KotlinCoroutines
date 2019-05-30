package com.lambdaschool.android_kotlin_coroutines

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperHeroResult(
    val response: String?="",
    @SerialName("results-for") val results_for: String?="",
    val results: List<SuperHero>?= listOf()
)

@Serializable
data class SuperHero(
    val id: String?="",
    val name: String?="",
    val powerstats: Powerstats?= Powerstats(),
    val biography: Biography?= Biography(),
    val appearance: Appearance?= Appearance(),
    val work: Work?= Work(),
    val connections: Connections?=Connections(),
    val image: Image?=Image()
)

@Serializable
data class Powerstats(
    val intelligence: String?="",
    val strength: String?="",
    val speed: String?="",
    val durability: String?="",
    val power: String?="",
    val combat: String?=""
)

@Serializable
data class Biography(
    @SerialName("full-name") val full_name: String?="",
    @SerialName("alter-egos") val alter_egos: String?="",
    val aliases: List<String>?= listOf(),
    @SerialName("place-of-birth") val place_of_birth: String?="",
    @SerialName("first-appearance") val first_appearance: String?="",
    val publisher: String?="",
    val alignment: String?=""
)

@Serializable
data class Appearance(
    val gender: String?="",
    val race: String?="",
    val height: List<String>?= listOf(),
    val weight: List<String>?= listOf(),
    @SerialName("eye-color") val eye_color: String?="",
    @SerialName("hair-color") val hair_color: String?=""
)

@Serializable
data class Work(
    val occupation: String?="",
    val base: String?=""
)

@Serializable
data class Connections(
    @SerialName("group-affiliation") val group_affiliation: String?="",
    val relatives: String?=""
)

@Serializable
data class Image(
    val url: String?=""
)


