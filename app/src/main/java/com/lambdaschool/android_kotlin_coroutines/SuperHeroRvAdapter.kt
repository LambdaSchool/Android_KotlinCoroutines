package com.lambdaschool.android_kotlin_coroutines

import android.app.Activity
import android.graphics.Bitmap
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_view_element.view.*
import kotlinx.android.synthetic.main.recycler_view_footer.view.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

class CharacterDiffTool(private val oldData: List<SuperHero>, private val newData: List<SuperHero>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].id == newData[newItemPosition].id

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]

}

class SuperHeroRvAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_FOOTER = 1
        private const val TYPE_ITEM = 2
    }

    private val data = mutableListOf<SuperHero>()
    private val dataJob = Job()
    private val workerScope = CoroutineScope(Dispatchers.Main + dataJob)

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val elementImageView: ImageView = view.element_image
        val elementTextView: TextView = view.element_text
        val imageJob = Job()
        val imageScope = CoroutineScope(Dispatchers.Main + imageJob)

        fun bindModel(superhero: SuperHero) {
            elementTextView.text = superhero.name
        }
    }

    open class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countTextView: TextView = view.count_view
    }

    init {
        getList(644)
    }

    private fun getList(id: Int = 1) {
        val adapter = this
        var oldData: MutableList<SuperHero> = mutableListOf()
        var diffResult: DiffUtil.DiffResult=DiffUtil.calculateDiff(CharacterDiffTool(oldData, data))

        workerScope.launch {
            withContext(Dispatchers.IO) {
                //Thread(Runnable {
                val result =
                    HisNetworkAdapter.httpGetRequest("https://www.superheroapi.com/api.php/10220044976853570/search/man")
                //HisNetworkAdapter.httpGetRequest("https://www.superheroapi.com/api.php/10220044976853570/$id",
                //object : HisNetworkAdapter.NetworkHttpCallback {
                //override fun returnResult(success: Boolean?, result: String) {
                oldData = mutableListOf<SuperHero>()
                oldData.addAll(data)

                val superheros: SuperHeroResult = Json.nonstrict.parse(SuperHeroResult.serializer(), result)
                data.addAll(superheros.results.orEmpty())

                diffResult = DiffUtil.calculateDiff(CharacterDiffTool(oldData, data))
            }
            //activity.runOnUiThread {
            if (oldData.size == 0) {
                notifyDataSetChanged()
            } else {
                diffResult.dispatchUpdatesTo(adapter)
            }
        }
        // }
        //}
        // })
        //}).start()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_FOOTER -> //Inflating footer view
                return FooterViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_view_footer,
                        parent,
                        false
                    )
                )
            else -> //Inflating recycle view item layout
                return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_view_element,
                        parent,
                        false
                    ) as View
                )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FooterViewHolder) {
            holder.countTextView.text = data.size.toString()
        } else {
            val itemHolder = holder as ViewHolder
            itemHolder.bindModel(data[position])
            if (position == (data.size - 4)) {
                getList(data.size)
            }

            holder.imageScope.launch {

                var image: Bitmap? = null

                withContext(Dispatchers.IO) {

                    val result = HisNetworkAdapter.getBitmapFromURL(data[position].image?.url ?: "")

                    if (result.first && result.second != null) {
                        image = result.second
                    }

                    image?.let { image = it }
                }

                image.let {

                    if (itemHolder.elementTextView.text == data[position].name) {
                        itemHolder.elementImageView.setImageBitmap(it)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size + 1

    override fun getItemViewType(position: Int): Int {
        return when {
            position >= data.size -> TYPE_FOOTER
            else -> TYPE_ITEM
        }
    }
}