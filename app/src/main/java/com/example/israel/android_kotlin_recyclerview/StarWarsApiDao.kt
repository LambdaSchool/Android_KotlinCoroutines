package com.example.israel.android_kotlin_recyclerview

import kotlinx.serialization.json.Json

class StarWarsApiDao {

    companion object {
        const val BASE_URL = "https://swapi.co/api/"

        fun getPeople(page: Int?) : ArrayList<StarWarsPerson>? {

            val urlStr = buildString {
                append(BASE_URL + "people/")
                if (page != null) {
                    append("?page=")
                    append(page)
                }
            }

            var out: ArrayList<StarWarsPerson>? = null
            NetworkAdapter.httpRequest(urlStr, NetworkAdapter.GET, null, null) { code, responseStr ->
                if (NetworkAdapter.isSuccessful(code)) {
                    val results = Json.nonstrict.parse(StarWarsPeopleResult.serializer(), responseStr!!)
                    out = results.results
                }
            }

            return out
        }
    }

}