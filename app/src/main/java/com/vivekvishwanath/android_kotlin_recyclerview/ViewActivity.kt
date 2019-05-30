package com.vivekvishwanath.android_kotlin_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ViewActivity : AppCompatActivity() {

    private lateinit var pokemonName: TextView
    private lateinit var pokemonImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        pokemonName = findViewById(R.id.view_pokemon_name)
        pokemonImage = findViewById(R.id.view_pokemon_image)
        pokemonName.text = pokemon.name
        Picasso.get().load(pokemon.sprites?.front_default).into(pokemonImage)
    }
}
