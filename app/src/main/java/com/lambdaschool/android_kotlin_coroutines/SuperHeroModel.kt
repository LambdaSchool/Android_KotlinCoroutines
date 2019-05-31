package com.lambdaschool.android_kotlin_coroutines

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperHeroResult(
    var response: String? = "",
    @SerialName("results-for") var results_for: String? = "",
    var results: List<SuperHero>? = listOf()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(SuperHero)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(response)
        parcel.writeString(results_for)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SuperHeroResult> {
        override fun createFromParcel(parcel: Parcel): SuperHeroResult {
            return SuperHeroResult(parcel)
        }

        override fun newArray(size: Int): Array<SuperHeroResult?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class SuperHero(
    var id: String? = "",
    var name: String? = "",
    var powerstats: Powerstats? = Powerstats(),
    var biography: Biography? = Biography(),
    var appearance: Appearance? = Appearance(),
    var work: Work? = Work(),
    var connections: Connections? = Connections(),
    var image: Image? = Image()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SuperHero> {
        override fun createFromParcel(parcel: Parcel): SuperHero {
            return SuperHero(parcel)
        }

        override fun newArray(size: Int): Array<SuperHero?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class Powerstats(
    var intelligence: String? = "",
    var strength: String? = "",
    var speed: String? = "",
    var durability: String? = "",
    var power: String? = "",
    var combat: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(intelligence)
        parcel.writeString(strength)
        parcel.writeString(speed)
        parcel.writeString(durability)
        parcel.writeString(power)
        parcel.writeString(combat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Powerstats> {
        override fun createFromParcel(parcel: Parcel): Powerstats {
            return Powerstats(parcel)
        }

        override fun newArray(size: Int): Array<Powerstats?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class Biography(
    @SerialName("full-name") var full_name: String? = "",
    @SerialName("alter-egos") var alter_egos: String? = "",
    var aliases: List<String>? = listOf(),
    @SerialName("place-of-birth") var place_of_birth: String? = "",
    @SerialName("first-appearance") var first_appearance: String? = "",
    var publisher: String? = "",
    var alignment: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(full_name)
        parcel.writeString(alter_egos)
        parcel.writeStringList(aliases)
        parcel.writeString(place_of_birth)
        parcel.writeString(first_appearance)
        parcel.writeString(publisher)
        parcel.writeString(alignment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Biography> {
        override fun createFromParcel(parcel: Parcel): Biography {
            return Biography(parcel)
        }

        override fun newArray(size: Int): Array<Biography?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class Appearance(
    var gender: String? = "",
    var race: String? = "",
    var height: List<String>? = listOf(),
    var weight: List<String>? = listOf(),
    @SerialName("eye-color") var eye_color: String? = "",
    @SerialName("hair-color") var hair_color: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gender)
        parcel.writeString(race)
        parcel.writeStringList(height)
        parcel.writeStringList(weight)
        parcel.writeString(eye_color)
        parcel.writeString(hair_color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Appearance> {
        override fun createFromParcel(parcel: Parcel): Appearance {
            return Appearance(parcel)
        }

        override fun newArray(size: Int): Array<Appearance?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class Work(
    var occupation: String? = "",
    var base: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(occupation)
        parcel.writeString(base)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Work> {
        override fun createFromParcel(parcel: Parcel): Work {
            return Work(parcel)
        }

        override fun newArray(size: Int): Array<Work?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class Connections(
    @SerialName("group-affiliation") var group_affiliation: String? = "",
    var relatives: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(group_affiliation)
        parcel.writeString(relatives)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Connections> {
        override fun createFromParcel(parcel: Parcel): Connections {
            return Connections(parcel)
        }

        override fun newArray(size: Int): Array<Connections?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
data class Image(
    var url: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}


