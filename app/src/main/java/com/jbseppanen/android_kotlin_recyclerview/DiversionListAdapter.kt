package com.jbseppanen.android_kotlin_recyclerview

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

const val TYPE_FOOTER = 0
const val TYPE_ITEM = 1

class DiversionListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class DiversionItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val diversionNameView: TextView = view.findViewById(R.id.text_diversion_name)
        val diversionPriceView: TextView = view.findViewById(R.id.text_diversion_price)
    }

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    private val data = mutableListOf<Diversion>()

    private val adapter = this

    init {
        getItems(15)
    }

    private fun getItems(qtyToGet: Int) {
        dataScope.launch {
            for (i in 0..qtyToGet) {
                val diversion = DataDao.getDiversion()
                if (diversion != null) {
                    data.add(diversion)
                    withContext(Dispatchers.Main) {
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_FOOTER -> {
                FooterViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.footer_element_layout,
                        parent,
                        false
                    )
                )
            }
            else -> {
                DiversionItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.diversion_item_layout,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position >= data.size -> TYPE_FOOTER
            else -> TYPE_ITEM
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, index: Int) {
        if (index == data.size - 5) {
            getItems(5)
        }


        if(getItemViewType(index) == TYPE_ITEM) {
            val element = data[index]
            val diversionHolder = viewHolder as DiversionItemViewHolder

            diversionHolder.itemView.setOnClickListener{
                val intent = Intent(diversionHolder.itemView.context, DetailActivity::class.java)
                val elementAsString = Json.stringify(Diversion.serializer(),element)
                intent.putExtra(DetailActivity.DETAIL_ITEM,elementAsString)
                startActivity(diversionHolder.itemView.context,intent,null)

            }

            diversionHolder.diversionNameView.text = element.activity
            val cost: String = when {
                element.price!! < .333 -> "$"
                element.price < .666 -> "$$"
                else -> "$$$"
            }
            diversionHolder.diversionPriceView.text = cost
        }
    }

    override fun getItemCount(): Int {
        return data.size + 1//add one for the footer
    }

}
