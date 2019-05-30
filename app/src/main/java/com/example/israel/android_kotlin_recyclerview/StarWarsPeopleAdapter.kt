package com.example.israel.android_kotlin_recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_person.view.*
import kotlinx.coroutines.*
import java.lang.RuntimeException

class StarWarsPeopleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_CONTENT = 0
        const val TYPE_FOOTER = 1
    }

    private var people = ArrayList<StarWarsPerson>()
    private var nextPage : Int? = null

    private val job = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return when (p1) {
            TYPE_CONTENT -> ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_person, p0, false))
            else -> FooterViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_footer, p0, false))
        }
    }

    override fun getItemCount(): Int {
        return people.size + 1
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when (getItemViewType(p1)) {
            TYPE_CONTENT -> {
                val contentVH = p0 as ViewHolder
                val person = people[p1]
                contentVH.bindObject(person)
            }

            TYPE_FOOTER -> {
                if (nextPage != null) {
                    dataScope.launch {
                        val starWarsPeopleResult = StarWarsApiDao.getPeople(nextPage)

                        if (starWarsPeopleResult?.results != null) {
                            withContext(Dispatchers.Main) {
                                val oldSize = people.size
                                people.addAll(starWarsPeopleResult.results!!)
                                if (starWarsPeopleResult.nextUrl != null) {
                                    nextPage = starWarsPeopleResult.nextUrl!!.last().toString().toInt()
                                } else {
                                    nextPage = null
                                }

                                notifyItemRangeChanged(oldSize, starWarsPeopleResult.results!!.size)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            people.size -> TYPE_FOOTER
            else -> TYPE_CONTENT
        }
    }

    fun setPeople(objects: ArrayList<StarWarsPerson>) {
        this.people = objects
        notifyDataSetChanged()
    }

    fun setNextUrl(nextPage: Int?) {
        this.nextPage = nextPage
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindObject(person: StarWarsPerson) {
            itemView.i_person_t_name.text = person.name + " " + (adapterPosition + 1)
        }
    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}