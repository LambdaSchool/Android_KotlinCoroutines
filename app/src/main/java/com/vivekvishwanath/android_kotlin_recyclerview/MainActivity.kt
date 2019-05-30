package com.vivekvishwanath.android_kotlin_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pokemonList = ArrayList<Pokemon>()
        val pokemonDao = PokemonApiDao()
        val viewAdapter = PokemonListAdapter(pokemonList, this)
        val viewManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        val dataJob = Job()
        val dataScope = CoroutineScope(Dispatchers.IO + dataJob)
        val mainScope = CoroutineScope(Dispatchers.Main + dataJob)
            findViewById<RecyclerView>(R.id.recycler_view).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        dataScope.launch {
            val allPokemon = pokemonDao.allPokemon
            for (i in 0 until (allPokemon?.results!!.size)) {
                pokemonDao.getSinglePokemon(allPokemon.results.get(i).name)?.let { pokemonList.add(it) }
                mainScope.launch {  viewAdapter.notifyDataSetChanged() }
            }
        }
    }
}

