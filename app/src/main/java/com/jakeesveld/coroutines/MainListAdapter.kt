package com.jakeesveld.coroutines

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainListAdapter(val dataList: List<com.jakeesveld.coroutines.Character>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    open class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val characterName: TextView = view.text_character_name
        val characterSpecies: TextView = view.text_character_species
        val characterGender: TextView = view.text_character_gender
        val characterStatus: TextView = view.text_character_status
        val characterImage: ImageView = view.character_image

        fun bindModel(character: com.jakeesveld.coroutines.Character){
            characterGender.text = "Gender: ${character.gender}"
            characterName.text = character.name
            characterSpecies.text = "Species: ${character.species}"
            characterStatus.text = "Status: ${character.status}"
            Picasso.get().load(character.image).into(characterImage)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.main_list_item, p0, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        val data: com.jakeesveld.coroutines.Character = dataList[position]
        val holder = vh as ViewHolder
        holder.bindModel(data)
    }
}