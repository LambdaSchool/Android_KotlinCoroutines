package com.jakeesveld.coroutines

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {

    val dataJob = Job()
    val dataScope = CoroutineScope(Dispatchers.Main + dataJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiURL = "https://rickandmortyapi.com/api/character"
        val characterList = mutableListOf<com.jakeesveld.coroutines.Character>()

        val recyclerView = recycler_view
        val listAdapter = MainListAdapter(characterList)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        dataScope.launch {
            var resultString: String = ""
            withContext(Dispatchers.IO){
                resultString = NetworkAdapter.httpGetRequest(apiURL)
            }
            val charactersList = Json.parse(Base.serializer(), resultString)

            charactersList.results?.forEach { characterList.add(it) }

            listAdapter.notifyDataSetChanged()
        }


    }
}
