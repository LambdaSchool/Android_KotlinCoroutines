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

class SuperHeroRvAdapter(val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_FOOTER = 1
        private const val TYPE_ITEM = 2
    }

    private val data = mutableListOf<SuperHero>()

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val elementImageView: ImageView = view.element_image
        val elementTextView: TextView = view.element_text

        fun bindModel(superhero: SuperHero) {
            elementTextView.text = superhero.name
        }
    }

    init {
        //for (i in 1..20 )
        getList(644)
    }

    open class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countTextView: TextView = view.count_view
    }

    private fun getList(id: Int = 1) {
        val adapter = this

        Thread(Runnable {
            HisNetworkAdapter.httpGetRequest("https://www.superheroapi.com/api.php/10220044976853570/search/man",
                //HisNetworkAdapter.httpGetRequest("https://www.superheroapi.com/api.php/10220044976853570/$id",
                object : HisNetworkAdapter.NetworkHttpCallback {
                    override fun returnResult(success: Boolean?, result: String) {
                        val oldData = mutableListOf<SuperHero>()
                        oldData.addAll(data)

                        val superheros: SuperHeroResult = Json.nonstrict.parse(SuperHeroResult.serializer(), result)
                        data.addAll(superheros.results.orEmpty())

                        val diffResult = DiffUtil.calculateDiff(CharacterDiffTool(oldData, data))

                        activity.runOnUiThread {
                            if (oldData.size == 0) {
                                notifyDataSetChanged()
                            } else {
                                diffResult.dispatchUpdatesTo(adapter)
                            }
                        }
                    }
                })
        }).start()
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
            Thread(Runnable {
                HisNetworkAdapter.getBitmapFromURL(
                    data[position].image?.url ?: "",
                    object : HisNetworkAdapter.NetworkBitmapCallback {

                        override fun returnResult(success: Boolean?, result: Bitmap?, requestUrl: String) {
                            if (itemHolder.elementTextView.text == data[position].name) {
                                activity.runOnUiThread {
                                    itemHolder.elementImageView.setImageBitmap(result)
                                }
                            } else {

                            }
                        }

                    })
            }).start()
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