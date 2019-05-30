package com.example.israel.android_kotlin_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val job = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + job)

    private val starWarsPeopleAdapter = StarWarsPeopleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a_r_star_wars.setHasFixedSize(true)
        a_r_star_wars.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        a_r_star_wars.adapter = starWarsPeopleAdapter

        dataScope.launch {
            val starWarsPeopleResult = StarWarsApiDao.getPeople(1)

            if (starWarsPeopleResult?.results != null) {
                withContext(Dispatchers.Main) {
                    if (starWarsPeopleResult.nextUrl != null) {
                        val nextPage = starWarsPeopleResult.nextUrl!!.last().toString().toInt()
                        starWarsPeopleAdapter.setNextUrl(nextPage)
                    }

                    starWarsPeopleAdapter.setPeople(starWarsPeopleResult.results!!)

                }
            }
        }

    }

}
