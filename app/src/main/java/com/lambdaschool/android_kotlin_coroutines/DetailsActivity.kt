package com.lambdaschool.android_kotlin_coroutines

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details_activity.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_activity)

        val superHeroId = intent.getStringExtra("superhero")
        var superHero: SuperHero = SuperHero()

        val dataJob = Job()
        val workerScope = CoroutineScope(Dispatchers.Main + dataJob)
        workerScope.launch {
            withContext(Dispatchers.IO) {
                val result =
                        HisNetworkAdapter.httpGetRequest("https://www.superheroapi.com/api.php/10220044976853570/$superHeroId")
                superHero = Json.nonstrict.parse(SuperHero.serializer(), result)
            }

            withContext(Dispatchers.Main) {

                details_text_view.text = ("full_name -> ${superHero.biography?.full_name}\n" +
                        "name -> ${superHero.name}\n" +
                        "place_of_birth -> ${superHero.biography?.place_of_birth}\n" +
                        "strength -> ${superHero.powerstats?.strength}\n" +
                        "durability -> ${superHero.powerstats?.durability}\n" +
                        "combat -> ${superHero.powerstats?.combat}\n" +
                        "power -> ${superHero.powerstats?.power}\n" +
                        "speed -> ${superHero.powerstats?.speed}\n" +
                        "intelligence -> ${superHero.powerstats?.intelligence}\n" +
                        "relatives -> ${superHero.connections?.relatives}\n" +
                        "group_affiliation -> ${superHero.connections?.group_affiliation}\n" +
                        "aliases -> ${superHero.biography?.aliases?.get(0)}\n" +
                        "first_appearance -> ${superHero.biography?.first_appearance}\n" +
                        "publisher -> ${superHero.biography?.publisher}\n" +
                        "alignment -> ${superHero.biography?.alignment}\n" +
                        "alter_egos -> ${superHero.biography?.alter_egos}\n" +
                        "occupation -> ${superHero.work?.occupation}\n" +
                        "base -> ${superHero.work?.base}\n" +
                        "eye_color -> ${superHero.appearance?.eye_color}\n" +
                        "gender -> ${superHero.appearance?.gender}\n" +
                        "race -> ${superHero.appearance?.race}\n" +
                        "weight -> ${superHero.appearance?.weight?.get(0)}\n" +
                        "height -> ${superHero.appearance?.height?.get(0)}\n" +
                        "hair_color -> ${superHero.appearance?.hair_color}\n" +
                        "image -> ${superHero.image?.url}\n" +
                        "id -> ${superHero.id}")
            }
        }

        val imageJob = Job()
        val imageScope = CoroutineScope(Dispatchers.Main + imageJob)
        imageScope.launch {

            var image: Bitmap? = null

            withContext(Dispatchers.IO) {
                dataWait()
                val result = HisNetworkAdapter.getBitmapFromURL(superHero.image?.url ?: "")

                if (result.first && result.second != null) {
                    image = result.second
                }
                image?.let { image = it }

                withContext(Dispatchers.Main) {

                    //dataWait()
                    image.let {
                        details_image_view.setImageBitmap(it)

                    }
                }
            }
        }
    }
}

private suspend fun dataWait() {
    delay(1000)
}