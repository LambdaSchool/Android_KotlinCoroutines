package com.joshuahalvorson.android_kotlin_coroutines.dao

import android.support.annotation.WorkerThread
import com.joshuahalvorson.android_kotlin_coroutines.model.Card
import com.joshuahalvorson.android_kotlin_coroutines.model.CardsList
import com.joshuahalvorson.android_kotlin_coroutines.network.NetworkAdapter
import kotlinx.serialization.json.Json

object MagicTheGatheringDao{
    const val CARDS_URL = "https://api.magicthegathering.io/v1/cards?pageSize=1&page=1231"

    interface CardsCallback{
        fun callback(list: List<Card>)
    }

    fun getCards(callback: CardsCallback){
        NetworkAdapter.httpGetRequest(CARDS_URL, object: NetworkAdapter.NetworkCallback {
            override fun returnResult(success: Boolean?, result: String) {
                if(success == true){
                    val cardsList = Json.parse(CardsList.serializer(), result)
                    callback.callback(cardsList.cards?: listOf())
                }
            }
        })
    }

    @WorkerThread
    suspend fun getCards(): List<Card>{
        val (success, result) = NetworkAdapter.httpGetRequest(CARDS_URL)
        var cardList: CardsList? = null
        if(success){
            cardList = Json.parse(CardsList.serializer(), result)
        }
        return cardList?.cards ?: listOf()
    }
}