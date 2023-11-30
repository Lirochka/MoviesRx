package com.example.moviesrx.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Movie(
    posterPath: String,
    @SerializedName("adult")
    var isAdult: Boolean,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    genreIds: List<Int>,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("vote_average")
    var voteAverage: Double?
) : Parcelable {
    @SerializedName("poster_path")
    var posterPath: String? = null
        get() = "https://image.tmdb.org/t/p/w500$field"

    constructor(parcel: Parcel) : this(
        TODO("posterPath"),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        TODO("genreIds"),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeValue(id)
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeValue(popularity)
        parcel.writeValue(voteCount)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
