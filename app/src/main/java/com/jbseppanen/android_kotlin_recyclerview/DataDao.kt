package com.jbseppanen.android_kotlin_recyclerview

import kotlinx.serialization.json.Json

object DataDao {

    private const val API_URL = "http://www.boredapi.com/api/activity/"

    interface DataCallback {
        fun callback(diversion: Diversion)
    }

    fun getDiversions(callback: DataCallback) {
        NetworkAdapter.httpRequest(API_URL, NetworkAdapter.GET, "", object : NetworkAdapter.NetworkCallback {
            override fun returnResult(success: Boolean?, result: String) {
                if (success == true) {
                    val diversion = Json.nonstrict.parse(Diversion.serializer(), result)
                    callback.callback(diversion)
                }
            }
        })
    }
}