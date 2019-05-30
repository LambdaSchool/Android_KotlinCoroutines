package com.vivekvishwanath.android_kotlin_recyclerview

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApiInterface {

    @GET
    fun getPokemonByName(@Url url: String): Call<JsonElement>

    @GET("pokemon")
    fun getAllPokemon(@Query("limit")limit: Int): Call<JsonElement>

}