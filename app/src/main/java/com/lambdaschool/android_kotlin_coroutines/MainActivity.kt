package com.lambdaschool.android_kotlin_coroutines

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.UnstableDefault

class MainActivity : AppCompatActivity() {

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var classNameReflection: String? = this::class.simpleName
        var methodNameReflection: String? = this::getLocalClassName.javaClass.enclosingMethod.name

        val viewAdapter = SuperHeroRvAdapter()
        recycler_view.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = viewAdapter
        }

/*        Thread(Runnable {
            HisNetworkAdapter.httpGetRequest("https://www.superheroapi.com/api.php/10220044976853570/644",
                object : HisNetworkAdapter.NetworkHttpCallback {
                    override fun returnResult(success: Boolean?, result: String) {

                        if (success!!) {
                            val superheros: SuperHero = Json.nonstrict.parse(SuperHero.serializer(), result)
                            Log.i(
                                "$classNameReflection",
                                "$methodNameReflection - ${Json.stringify(SuperHero.serializer(), superheros)}"
                            )
                        }
                    }

                })
        }).start()*/

/*        val superHerosHardCodedObject: SuperHeroResult =
            Json.nonstrict.parse(SuperHeroResult.serializer(), superHeroHardCodedString2)
        Log.i(
            "$classNameReflection",
            "$methodNameReflection - ${Json.stringify(SuperHeroResult.serializer(), superHerosHardCodedObject)}"
        )*/
    }

    val superHeroHardCodedString2: String =
        "{\"response\":\"success\",\"results-for\":\"woman\",\"results\":[{\"id\":\"73\",\"name\":\"Batwoman V\",\"powerstats\":{\"intelligence\":\"81\",\"strength\":\"8\",\"speed\":\"29\",\"durability\":\"25\",\"power\":\"27\",\"combat\":\"80\"},\"biography\":{\"full-name\":\"Katherine Rebecca Kane\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"52 #7 (June, 2006)\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Human\",\"height\":[\"5'10\",\"178 cm\"],\"weight\":[\"- lb\",\"0 kg\"],\"eye-color\":\"Green\",\"hair-color\":\"Red\"},\"work\":{\"occupation\":\"-\",\"base\":\"Gotham City\"},\"connections\":{\"group-affiliation\":\"Batman Family (unofficially), D.E.O, Unknowns\",\"relatives\":\"Jacob Kane (father), Gabi Kane (mother; deceased), Beth Kane (twin sister), Mary Elizabeth \\\"Bette\\\" Kane (cousin), Catherine Hamilton-Kane (stepmother), Philip Kane (uncle), Kane Family\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/10234.jpg\"}},{\"id\":\"87\",\"name\":\"Bionic Woman\",\"powerstats\":{\"intelligence\":\"56\",\"strength\":\"37\",\"speed\":\"33\",\"durability\":\"40\",\"power\":\"20\",\"combat\":\"40\"},\"biography\":{\"full-name\":\"Jamie Sommers\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Clarissa Whyte\"],\"place-of-birth\":\"-\",\"first-appearance\":\"-\",\"publisher\":\"\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Cyborg\",\"height\":[\"-\",\"0 cm\"],\"weight\":[\"- lb\",\"0 kg\"],\"eye-color\":\"Blue\",\"hair-color\":\"Black\"},\"work\":{\"occupation\":\"-\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"-\",\"relatives\":\"-\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/10648.jpg\"}},{\"id\":\"165\",\"name\":\"Catwoman\",\"powerstats\":{\"intelligence\":\"69\",\"strength\":\"11\",\"speed\":\"33\",\"durability\":\"28\",\"power\":\"27\",\"combat\":\"85\"},\"biography\":{\"full-name\":\"Selina Kyle\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"The Cat\",\"Irena Dubrovna\",\"Elva Barr\",\"Sadie Kelowski\",\"Madame Moderne\",\"Marguerite Tone\"],\"place-of-birth\":\"Gotham City\",\"first-appearance\":\"Detective Comics #569\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Human\",\"height\":[\"5'9\",\"175 cm\"],\"weight\":[\"135 lb\",\"61 kg\"],\"eye-color\":\"Green\",\"hair-color\":\"Black\"},\"work\":{\"occupation\":\"Thief\",\"base\":\"Gotham City\"},\"connections\":{\"group-affiliation\":\"Batman Family, Outsiders; formerly Birds of Prey, Injustice League, Secret Society of Super-Villains\",\"relatives\":\"Brian Kyle (father, deceased), Maria Kyle (mother, deceased), Maggie Kyle (sister), Helena Kyle (daughter)\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/659.jpg\"}},{\"id\":\"317\",\"name\":\"Hawkwoman\",\"powerstats\":{\"intelligence\":\"null\",\"strength\":\"96\",\"speed\":\"null\",\"durability\":\"null\",\"power\":\"null\",\"combat\":\"null\"},\"biography\":{\"full-name\":\"Shayera Hol\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Shayera Hall\",\"Hawkgirl\"],\"place-of-birth\":\"-\",\"first-appearance\":\"The Brave and the Bold #34 (February-March 1961)\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"5'9\",\"175 cm\"],\"weight\":[\"120 lb\",\"54 kg\"],\"eye-color\":\"Green\",\"hair-color\":\"Red\"},\"work\":{\"occupation\":\"-\",\"base\":\"Midway City, Michigan; Justice League Satellite I\"},\"connections\":{\"group-affiliation\":\"Justice League of America\",\"relatives\":\"-\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/702.jpg\"}},{\"id\":\"318\",\"name\":\"Hawkwoman II\",\"powerstats\":{\"intelligence\":\"null\",\"strength\":\"null\",\"speed\":\"null\",\"durability\":\"null\",\"power\":\"null\",\"combat\":\"null\"},\"biography\":{\"full-name\":\"Sharon Parker\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"-\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"-\",\"0 cm\"],\"weight\":[\"- lb\",\"0 kg\"],\"eye-color\":\"-\",\"hair-color\":\"-\"},\"work\":{\"occupation\":\"-\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"-\",\"relatives\":\"-\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/1129.jpg\"}},{\"id\":\"319\",\"name\":\"Hawkwoman III\",\"powerstats\":{\"intelligence\":\"null\",\"strength\":\"92\",\"speed\":\"null\",\"durability\":\"null\",\"power\":\"null\",\"combat\":\"null\"},\"biography\":{\"full-name\":\"Shayera Thal\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"-\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"5'7\",\"170 cm\"],\"weight\":[\"145 lb\",\"65 kg\"],\"eye-color\":\"Blue\",\"hair-color\":\"Red\"},\"work\":{\"occupation\":\"Police Officer\",\"base\":\"Detroit; Thanagar\"},\"connections\":{\"group-affiliation\":\"-\",\"relatives\":\"-\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/1130.jpg\"}},{\"id\":\"344\",\"name\":\"Invisible Woman\",\"powerstats\":{\"intelligence\":\"88\",\"strength\":\"10\",\"speed\":\"27\",\"durability\":\"85\",\"power\":\"93\",\"combat\":\"56\"},\"biography\":{\"full-name\":\"Susan Storm Richards\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Invisible Girl\"],\"place-of-birth\":\"Glenville, Long Island, New York\",\"first-appearance\":\"FANTASTIC FOUR #1\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Human \\/ Radiation\",\"height\":[\"5'6\",\"168 cm\"],\"weight\":[\"120 lb\",\"54 kg\"],\"eye-color\":\"blue\",\"hair-color\":\"Blond\"},\"work\":{\"occupation\":\"Member of the Fantastic Four, financial manager for Fantastic Four Inc, occasional model and actress\",\"base\":\"Pier Four, New York City\"},\"connections\":{\"group-affiliation\":\"Fantastic Four, formerly; Secret Avengers, Avengers, Brides of Set, Pawns of Death\",\"relatives\":\"Reed Richards (Mister Fantastic, husband), Franklin Richards (son), Valeria Richards (daughter), Johnny Storm (Human Torch, brother), Franklin Storm (father, deceased), Mary Storm (mother, deceased), Marygay Jewel Dinkins (aunt), \\\"Bones\\\" (cousin), Evelyn Richards (mother-in-law, deceased), Nathaniel Richards (father-in-law), Cassandra Richards (Warlord, mother-in-law, deceased), unnamed child of Nathaniel Richards (brother-in-law), Tara Richards (Huntara, sister-in-law), Kristoff Vernard (alleged brother-in-law), Lyja (sister-in-law)\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/620.jpg\"}},{\"id\":\"623\",\"name\":\"Spider-Woman\",\"powerstats\":{\"intelligence\":\"56\",\"strength\":\"42\",\"speed\":\"42\",\"durability\":\"60\",\"power\":\"68\",\"combat\":\"70\"},\"biography\":{\"full-name\":\"Jessica Drew\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Arachne\",\"Ariadne Hyde\",\"Dark Angel\",\"Dark Angel of San Francisco\"],\"place-of-birth\":\"London, England\",\"first-appearance\":\"Marvel Spotlight #32 (February, 1977)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Human\",\"height\":[\"5'10\",\"178 cm\"],\"weight\":[\"130 lb\",\"59 kg\"],\"eye-color\":\"Green\",\"hair-color\":\"Black\"},\"work\":{\"occupation\":\"Form agent of HYDRA, former bounty hunter, private investigator, adventurer\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Former agent of HYDRA\",\"relatives\":\"Jonathan (father, deceased), Merriem (mother, deceased)\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/481.jpg\"}},{\"id\":\"624\",\"name\":\"Spider-Woman II\",\"powerstats\":{\"intelligence\":\"null\",\"strength\":\"null\",\"speed\":\"null\",\"durability\":\"null\",\"power\":\"null\",\"combat\":\"null\"},\"biography\":{\"full-name\":\"\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"-\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"-\",\"0 cm\"],\"weight\":[\"- lb\",\"0 kg\"],\"eye-color\":\"-\",\"hair-color\":\"-\"},\"work\":{\"occupation\":\"-\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"-\",\"relatives\":\"-\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/483.jpg\"}},{\"id\":\"625\",\"name\":\"Spider-Woman III\",\"powerstats\":{\"intelligence\":\"50\",\"strength\":\"48\",\"speed\":\"27\",\"durability\":\"42\",\"power\":\"60\",\"combat\":\"28\"},\"biography\":{\"full-name\":\"Martha Franklin\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"Rochester, New York\",\"first-appearance\":\"(cameo) Spectacular Spider-Man #263 (1998); (full) Amazing Spider-Man #441 (1998)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"5'8\",\"173 cm\"],\"weight\":[\"123 lb\",\"55 kg\"],\"eye-color\":\"Brown\",\"hair-color\":\"Brown\"},\"work\":{\"occupation\":\"-\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Formerly Gathering of the Five\",\"relatives\":\"Jeremy Franklin (father, deceased), Bernice Franklin (mother, deceased), J. Jonah Jameson (foster father), Marla Madison (foster mother)\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/482.jpg\"}},{\"id\":\"626\",\"name\":\"Spider-Woman IV\",\"powerstats\":{\"intelligence\":\"null\",\"strength\":\"null\",\"speed\":\"null\",\"durability\":\"null\",\"power\":\"null\",\"combat\":\"null\"},\"biography\":{\"full-name\":\"Charlotte Witter\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Spider-Woman\"],\"place-of-birth\":\"-\",\"first-appearance\":\"Amazing Spider-Man Volume 2 #5 (# 446)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"bad\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"5'10\",\"178 cm\"],\"weight\":[\"128 lb\",\"58 kg\"],\"eye-color\":\"Red\",\"hair-color\":\"White\"},\"work\":{\"occupation\":\"Fashion designer, professional criminal\",\"base\":\"New York City\"},\"connections\":{\"group-affiliation\":\"-\",\"relatives\":\"Cassandra Webb (aka Madame Web, grandmother)\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/883.jpg\"}},{\"id\":\"720\",\"name\":\"Wonder Woman\",\"powerstats\":{\"intelligence\":\"88\",\"strength\":\"100\",\"speed\":\"79\",\"durability\":\"100\",\"power\":\"100\",\"combat\":\"100\"},\"biography\":{\"full-name\":\"Diana Prince\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Princess Diana\",\"Princess of the Amazons\",\"Goddess of Truth\",\" Wondy\",\"Wonder Girl\",\"The Amazon Princess\"],\"place-of-birth\":\"Themyscira\",\"first-appearance\":\"All-Star Comics #8 (December, 1941)\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Amazon\",\"height\":[\"6'0\",\"183 cm\"],\"weight\":[\"165 lb\",\"74 kg\"],\"eye-color\":\"Blue\",\"hair-color\":\"Black\"},\"work\":{\"occupation\":\"Adventurer, Emissary to the world of Man, Protector of Paradise Island; former Goddess of Truth\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Justice League of America, Justice Society of America (pre-Crisis Earth-2 version); All-Star Squadron (pre-Crisis Earth-2 version)\",\"relatives\":\"Queen Hippolyta (mother, deceased), Donna Troy (Troia) (magically-created duplicate)\"},\"image\":{\"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/807.jpg\"}}]}"

    val superHeroHardCodedString1: String = ("{\n" +
            "   \"response\":\"success\",\n" +
            "   \"id\":\"644\",\n" +
            "   \"name\":\"Superman\",\n" +
            "   \"powerstats\":{\n" +
            "      \"intelligence\":\"94\",\n" +
            "      \"strength\":\"100\",\n" +
            "      \"speed\":\"100\",\n" +
            "      \"durability\":\"100\",\n" +
            "      \"power\":\"100\",\n" +
            "      \"combat\":\"85\"\n" +
            "   },\n" +
            "   \"biography\":{\n" +
            "      \"full-name\":\"Clark Kent\",\n" +
            "      \"alter-egos\":\"Superman Prime One-Million\",\n" +
            "      \"aliases\":[\n" +
            "         \"Clark Joseph Kent\",\n" +
            "         \"The Man of Steel\",\n" +
            "         \"the Man of Tomorrow\",\n" +
            "         \"the Last Son of Krypton\",\n" +
            "         \"Big Blue\",\n" +
            "         \"the Metropolis Marvel\",\n" +
            "         \"the Action Ace\"\n" +
            "      ],\n" +
            "      \"place-of-birth\":\"Krypton\",\n" +
            "      \"first-appearance\":\"ACTION COMICS #1\",\n" +
            "      \"publisher\":\"Superman Prime One-Million\",\n" +
            "      \"alignment\":\"good\"\n" +
            "   },\n" +
            "   \"appearance\":{\n" +
            "      \"gender\":\"Male\",\n" +
            "      \"race\":\"Kryptonian\",\n" +
            "      \"height\":[\n" +
            "         \"6'3\",\n" +
            "         \"191 cm\"\n" +
            "      ],\n" +
            "      \"weight\":[\n" +
            "         \"225 lb\",\n" +
            "         \"101 kg\"\n" +
            "      ],\n" +
            "      \"eye-color\":\"Blue\",\n" +
            "      \"hair-color\":\"Black\"\n" +
            "   },\n" +
            "   \"work\":{\n" +
            "      \"occupation\":\"Reporter for the Daily Planet and novelist\",\n" +
            "      \"base\":\"Metropolis\"\n" +
            "   },\n" +
            "   \"connections\":{\n" +
            "      \"group-affiliation\":\"Justice League of America, The Legion of Super-Heroes (pre-Crisis as Superboy); Justice Society of America (pre-Crisis Earth-2 version); All-Star Squadron (pre-Crisis Earth-2 version)\",\n" +
            "      \"relatives\":\"Lois Lane (wife), Jor-El (father, deceased), Lara (mother, deceased), Jonathan Kent (adoptive father), Martha Kent (adoptive mother), Seyg-El (paternal grandfather, deceased), Zor-El (uncle, deceased), Alura (aunt, deceased), Supergirl (Kara Zor-El, cousin), Superboy (Kon-El\\/Conner Kent, partial clone)\"\n" +
            "   },\n" +
            "   \"image\":{\n" +
            "      \"url\":\"https:\\/\\/www.superherodb.com\\/pictures2\\/portraits\\/10\\/100\\/791.jpg\"\n" +
            "   }\n" +
            "}")

}
