package com.jakeesveld.coroutines

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.main_list_item.view.*
import java.lang.Character

class MainListAdapter(val dataList: List<com.jakeesveld.coroutines.Character>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    open class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val characterName: TextView = view.text_character_name
        val characterSpecies: TextView = view.text_character_species
        val characterGender: TextView = view.text_character_gender
        val characterStatus: TextView = view.text_character_status

        fun bindModel(character: com.jakeesveld.coroutines.Character){
            characterGender.text = character.gender
            characterName.text = character.name
            characterSpecies.text = character.species
            characterStatus.text = character.status
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