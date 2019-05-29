package com.example.israel.android_kotlin_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val starWarsPeopleAdapter = StarWarsPeopleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a_r_star_wars.setHasFixedSize(true)
        a_r_star_wars.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        a_r_star_wars.adapter = starWarsPeopleAdapter

        Thread(Runnable {
            val results = StarWarsApiDao.getPeople(1)

            if (results != null) {
                runOnUiThread {
                    starWarsPeopleAdapter.setPeople(results)
                }
            }
        }).start()



    }

}
