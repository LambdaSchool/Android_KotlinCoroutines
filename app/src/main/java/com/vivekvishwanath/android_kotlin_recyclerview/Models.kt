package com.vivekvishwanath.android_kotlin_recyclerview

import kotlinx.serialization.Serializable

@Serializable
data class AllPokemon(val count: Int?, val next: String?, val previous: String?,
                      val results: List<Results>?): java.io.Serializable

@Serializable
data class Results(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Abilities(val ability: Ability?, val is_hidden: Boolean?, val slot: Int?): java.io.Serializable

@Serializable
data class Ability(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Pokemon(val abilities: List<Abilities>?, val base_experience: Int?,
                val forms: List<Forms>?, val game_indices: List<GameIndices>?,
                val height: Int?, val held_items: List<HeldItems>?, val id: Int?,
                val is_default: Boolean?, val location_area_encounters: String?,
                val moves: List<Moves>?, val name: String?, val order: Int?, val species: Species?,
                val sprites: Sprites?, val stats: List<Stats>?, val types: List<Types>?, val weight: Int?) : java.io.Serializable

@Serializable
data class Forms(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class GameIndices(val game_index: Int?, val version: Version?): java.io.Serializable

@Serializable
data class HeldItems(val item: Item?, val version_details: List<VersionDetails>?): java.io.Serializable

@Serializable
data class Item(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Move(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class MoveLearnMethod(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Moves(val move: Move?, val version_group_details: List<VersionGroupDetails>?): java.io.Serializable

@Serializable
data class Species(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Sprites(val back_default: String?, val back_female: String?, val back_shiny: String?,
                   val back_shiny_female: String?, val front_default: String?, val front_female: String?,
                   val front_shiny: String?, val front_shiny_female: String?): java.io.Serializable

@Serializable
data class Stat(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Stats(val base_stat: Int?, val effort: Int?, val stat: Stat?): java.io.Serializable

@Serializable
data class Type(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class Types(val slot: Int?, val type: Type?): java.io.Serializable

@Serializable
data class Version(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class VersionDetails(val rarity: Int?, val version: Version?): java.io.Serializable

@Serializable
data class VersionGroup(val name: String?, val url: String?): java.io.Serializable

@Serializable
data class VersionGroupDetails(val level_learned_at: Int?, val move_learn_method: MoveLearnMethod?,
                               val version_group: VersionGroup?): java.io.Serializable
