package com.joshuahalvorson.android_kotlin_recyclerview.dao

import com.joshuahalvorson.android_kotlin_recyclerview.model.Card
import com.joshuahalvorson.android_kotlin_recyclerview.model.CardsList
import com.joshuahalvorson.android_kotlin_recyclerview.network.NetworkAdapter
import kotlinx.serialization.json.Json
import kotlinx.serialization.set

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
}