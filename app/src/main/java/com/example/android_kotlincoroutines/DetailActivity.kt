package com.example.android_kotlincoroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*
class DetailActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_ITEM = "DetailItem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val diversionKey = intent.getStringExtra(DETAIL_ITEM)

        val dataJob = Job()
        val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

        dataScope.launch {
            val diversion = DataDao.getDiversionByKey(diversionKey)
            if (diversion != null) {
                withContext(Dispatchers.Main) {
                    detail_name.text = diversion.activity
                    detail_type.text = diversion.type
                    detail_access.text = diversion.accessibility.toString()
                    detail_participants.text = diversion.participants.toString()
                    val cost: String = when {
                        diversion.price!! < .333 -> "$"
                        diversion.price < .666 -> "$$"
                        else -> "$$$"
                    }
                    detail_price.text = cost
                    if (diversion.link != null) {
                        detail_link.text = diversion.link
                    }
                }
            }
        }
    }
}
