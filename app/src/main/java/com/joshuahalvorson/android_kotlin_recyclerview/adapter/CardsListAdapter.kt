package com.joshuahalvorson.android_kotlin_recyclerview.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.joshuahalvorson.android_kotlin_recyclerview.R
import com.joshuahalvorson.android_kotlin_recyclerview.dao.MagicTheGatheringDao
import com.joshuahalvorson.android_kotlin_recyclerview.model.Card
import com.squareup.picasso.Picasso

class CardsListAdapter(val activity: Activity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val cardList = mutableListOf<Card>()

    init {
        MagicTheGatheringDao.getCards(object : MagicTheGatheringDao.CardsCallback {
            override fun callback(list: List<Card>) {
                cardList.addAll(list)
                activity.runOnUiThread { notifyDataSetChanged() }
            }
        })
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardImage: ImageView = view.findViewById(R.id.element_card_image)
        val cardName: TextView = view.findViewById(R.id.element_card_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_list_element_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, index: Int) {
        val card = cardList[index]
        val cardHolder = viewHolder as ViewHolder
        cardHolder.cardName.text = card.name
        Picasso.get()
            .load(card.imageUrl)
            .into(viewHolder.cardImage)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}