package com.damakable.kotlinnews.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsItem(
    @SerializedName("kind") val kind: String?,
    @SerializedName("data") val data: NewsItemData?

) : Parcelable {
    fun title() : String = data?.title ?: ""
    fun body(): String = data?.selftext ?: ""

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable<NewsItemData>(NewsItemData::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kind)
        parcel.writeParcelable(data, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }
}
