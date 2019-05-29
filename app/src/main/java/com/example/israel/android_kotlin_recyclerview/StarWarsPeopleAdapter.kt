package com.example.israel.android_kotlin_recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_person.view.*

class StarWarsPeopleAdapter : RecyclerView.Adapter<StarWarsPeopleAdapter.ViewHolder>() {
    private var people = ArrayList<StarWarsPerson>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_person, p0, false))
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val person = people[p1]
        p0.bindObject(person)
    }

    fun setPeople(objects: ArrayList<StarWarsPerson>) {
        this.people = objects
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindObject(person: StarWarsPerson) {
            itemView.i_person_name.text = person.name
        }
    }


}