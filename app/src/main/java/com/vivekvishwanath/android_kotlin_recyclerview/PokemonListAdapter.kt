package com.vivekvishwanath.android_kotlin_recyclerview

import android.content.Context
import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.footer_layout.view.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*
import org.w3c.dom.Text

class PokemonListAdapter(pokemonList: ArrayList<Pokemon>, context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val data = pokemonList
    private val context = context

    open class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val pokemonImageView: ImageView = view.pokemon_image
        val pokemonNameView: TextView = view.pokemon_name
        val pokemonItemParent : LinearLayout = view.pokemon_item_parent

        fun bindModel(pokemon: Pokemon){
            pokemonNameView.text = pokemon.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).
            inflate(R.layout.pokemon_list_item, parent, false) as View)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ViewHolder
        holder.bindModel(data[position])
        Picasso.get().load(data[position].sprites?.front_default).into(itemHolder.pokemonImageView)

         itemHolder.pokemonItemParent.setOnClickListener{
             val intent = Intent(context, ViewActivity::class.java)
            intent.putExtra("pokemon", data[position])
            context.startActivity(intent)
        }
    }


}