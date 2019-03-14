package com.jbseppanen.android_kotlin_recyclerview

import kotlinx.serialization.json.Json

object DataDao {

    private const val API_URL = "http://www.boredapi.com/api/activity/"

    interface DataCallback {
        fun callback(diversion: Diversion)
    }

    fun getDiversionsWithCallback(callback: DataCallback) {
        NetworkAdapter.httpRequestWCallback(
            API_URL,
            NetworkAdapter.GET,
            "",
            object : NetworkAdapter.NetworkCallback {
                override fun returnResult(success: Boolean?, result: String) {
                    if (success == true) {
                        val diversion = Json.nonstrict.parse(Diversion.serializer(), result)
                        callback.callback(diversion)
                    }
                }
            })
    }


    fun getDiversion(): Diversion? {
        val (success, result) = NetworkAdapter.httpRequest(API_URL, NetworkAdapter.GET, "")
        var diversion: Diversion? = null
        if (success == true) {
            diversion = Json.nonstrict.parse(Diversion.serializer(), result)
        }
        return diversion ?: Diversion()
    }
}