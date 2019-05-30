package com.example.israel.android_kotlin_recyclerview

import android.support.annotation.WorkerThread
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class StarWarsApiDao {

    companion object {
        const val BASE_URL = "https://swapi.co/api/"

        @WorkerThread
        suspend fun getPeople(page: Int?) : StarWarsPeopleResult? {

            val urlStr = buildString {
                append(BASE_URL + "people/")
                if (page != null) {
                    append("?page=")
                    append(page)
                }
            }

            var out: StarWarsPeopleResult? = null
            NetworkAdapter.httpRequest(urlStr, NetworkAdapter.GET, null, null) { code, responseStr ->
                if (NetworkAdapter.isSuccessful(code)) {
                    out = Json.nonstrict.parse(StarWarsPeopleResult.serializer(), responseStr!!)
                }
            }

            return out
        }
    }

}