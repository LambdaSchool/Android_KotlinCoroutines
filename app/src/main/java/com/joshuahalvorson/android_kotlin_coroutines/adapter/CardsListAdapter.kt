package com.joshuahalvorson.android_kotlin_coroutines.adapter

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.joshuahalvorson.android_kotlin_coroutines.CardDetailActivity
import com.joshuahalvorson.android_kotlin_coroutines.R
import com.joshuahalvorson.android_kotlin_coroutines.dao.MagicTheGatheringDao
import com.joshuahalvorson.android_kotlin_coroutines.model.Card
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class CardsListAdapter(val activity: Activity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val cardList = mutableListOf<Card>()

    private val adapter = this
    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    init {
        /*MagicTheGatheringDao.getCards(object : MagicTheGatheringDao.CardsCallback {
            override fun callback(list: List<Card>) {
                cardList.addAll(list)
                activity.runOnUiThread { notifyDataSetChanged() }
            }
        })*/
        getCards()
    }

    private fun getCards(){
        dataScope.launch {
            val cardsList = MagicTheGatheringDao.getCards()
            cardList.addAll(cardsList)
            withContext(Dispatchers.Main){
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardName: TextView = view.findViewById(R.id.element_card_name)
        val parent: CardView = view.findViewById(R.id.parent_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_list_element_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, index: Int) {
        val card = cardList[index]
        val cardHolder = viewHolder as ViewHolder
        cardHolder.cardName.text = card.name
        cardHolder.parent.setOnClickListener {
            val intent = Intent(activity.applicationContext, CardDetailActivity::class.java)
            intent.putExtra("card", card.imageUrl)
            startActivity(cardHolder.parent.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}