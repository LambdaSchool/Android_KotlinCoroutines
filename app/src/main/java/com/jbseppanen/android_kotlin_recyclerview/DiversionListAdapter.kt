package com.jbseppanen.android_kotlin_recyclerview

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.*

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
        getItems(5)
    }

    private fun getItems(qtyToGet: Int) {
        dataScope.launch {
            val oldData = mutableListOf<Diversion>()
            oldData.addAll(data)
            for (i in 0..qtyToGet) {
                val diversion = DataDao.getRandomDiversion()
                if (diversion != null) {
                    data.add(diversion)
                }
            }
            val diffResult = DiffUtil.calculateDiff(CharacterDiffTool(oldData, data))
            withContext(Dispatchers.Main) {
                diffResult.dispatchUpdatesTo(adapter)
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
                intent.putExtra(DetailActivity.DETAIL_ITEM,element.key)
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

class CharacterDiffTool(private val oldData: List<Diversion>, private val newData: List<Diversion>): DiffUtil.Callback() {
    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldData[p0].key == newData[p1].key
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return oldData[p0] == newData[p1]
    }
}
