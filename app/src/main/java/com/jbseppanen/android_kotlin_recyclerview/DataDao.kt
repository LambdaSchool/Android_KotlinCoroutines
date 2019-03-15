package com.jbseppanen.android_kotlin_recyclerview

import kotlinx.serialization.json.Json

object DataDao {

    private const val API_URL = "http://www.boredapi.com/api/activity/"
    private const val KEY_URL = "?key="


    fun getRandomDiversion(): Diversion? {
        val (success, result) = NetworkAdapter.httpRequest(API_URL, NetworkAdapter.GET, "")
        var diversion: Diversion? = null
        if (success == true) {
            diversion = Json.nonstrict.parse(Diversion.serializer(), result)
        }
        return diversion ?: Diversion()
    }

    fun getDiversionByKey(key:String): Diversion? {
        val (success, result) = NetworkAdapter.httpRequest("$API_URL$KEY_URL$key", NetworkAdapter.GET, "")
        var diversion: Diversion? = null
        if (success == true) {
            diversion = Json.nonstrict.parse(Diversion.serializer(), result)
        }
        return diversion ?: Diversion()
    }
}