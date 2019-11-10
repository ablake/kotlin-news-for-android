package com.damakable.kotlinnews.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsItemData(
    @SerializedName("title") val title: String?,
    @SerializedName("selftext") val selftext: String?,
    @SerializedName("thumbnail") val thumbnail: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(selftext)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsItemData> {
        override fun createFromParcel(parcel: Parcel): NewsItemData {
            return NewsItemData(parcel)
        }

        override fun newArray(size: Int): Array<NewsItemData?> {
            return arrayOfNulls(size)
        }
    }
}
