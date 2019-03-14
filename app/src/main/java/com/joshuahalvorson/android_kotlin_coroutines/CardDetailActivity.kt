package com.joshuahalvorson.android_kotlin_coroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.joshuahalvorson.android_kotlin_coroutines.dao.MagicTheGatheringDao
import kotlinx.coroutines.*

class CardDetailActivity : AppCompatActivity() {
    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)
        val imageString = intent.getStringExtra("card")
        getCards(imageString)
    }

    private fun getCards(imageString: String){
        dataScope.launch {
            val cardImage = MagicTheGatheringDao.getCardImage(imageString)
            withContext(Dispatchers.Main){
                findViewById<ImageView>(R.id.card_iamge).setImageBitmap(cardImage)
            }
        }
    }
}
