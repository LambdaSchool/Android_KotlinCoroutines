package com.example.myapplication

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.*

class NumbersListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val inflatedView = LayoutInflater.from(p0.context).inflate(R.layout.number_list_item, p0, false)
        return NumbersItemViewHolder(inflatedView)
    }

    class NumbersItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val numberValueView: TextView = view.findViewById(R.id.tv_number)
        val numberFactView: TextView = view.findViewById(R.id.tv_fact)
    }

    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    private val data = mutableListOf<Number>()

    private val adapter = this

    init {
        getItems()
    }

    private fun getItems() {

        dataScope.launch {
            val oldData = mutableListOf<Number>()
            oldData.addAll(data)

            val diffResult = DiffUtil.calculateDiff(NumberDiffTool(oldData, data))

            withContext(Dispatchers.Main) {
                diffResult.dispatchUpdatesTo(adapter)
            }
        }
    }


    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val element = data[p1]
        val numberHolder = p0 as NumbersItemViewHolder

        numberHolder.numberFactView.text = element.fact.toString()
        numberHolder.numberValueView.text = element.number
    }
}

class NumberDiffTool(private val oldData: List<Number>, private val newData: List<Number>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldData[p0].number == newData[p1].number
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

