package com.vivekvishwanath.android_kotlin_recyclerview

import android.support.annotation.WorkerThread
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonApiDao{

    private val BASE_URL = "https://pokeapi.co/api/v2/"
    private val SINGLE_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/" + "%s"

    private var retrofit = Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
    private var pokemonApiInterface = retrofit.create(PokemonApiInterface::class.java)
    private var gson = Gson()

    val allPokemon: AllPokemon?
    get() {
        val call = pokemonApiInterface.getAllPokemon(500)
        val allPokemonJson = call.execute().body()
        if (allPokemonJson != null) {
            val allPokemon = gson.fromJson(allPokemonJson, AllPokemon::class.java)
            return allPokemon
        }
        return null
    }

    @WorkerThread
    fun getSinglePokemon(idOrName: String?) : Pokemon? {
        val url = SINGLE_POKEMON_URL.format(idOrName)
        val call = pokemonApiInterface.getPokemonByName(url)
        val singlePokemonJson = call.execute().body()
        if (singlePokemonJson != null) {
            val pokemon = gson.fromJson(singlePokemonJson, Pokemon::class.java)
            return pokemon
        }
        return null
    }

}