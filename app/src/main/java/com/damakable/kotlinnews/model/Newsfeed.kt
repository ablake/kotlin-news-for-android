package com.damakable.kotlinnews.model

import com.google.gson.annotations.SerializedName

/**
 * Data returned includes fields like these:
 * {
 *   "kind":"Listing",
 *   "data":{
 *     "after":"t3_dr3xzu",
 *     "before":null
 *     "children":[
 *       {
 *         "kind":"t3",
 *         "data":{
 *           "title":"Kotlin is not Android",
 *           selftext:"", // These can be blank, or the body of a post
 *           selftext_html:"",
 *           "permalink":"/r/Kotlin/comments/dsg96y/kotlin_is_not_android/",
 *           "url":"https://blog.kotlin-academy.com/kotlin-is-not-android-c96984730c35",
 *           "thumbnail":""
 *         }
 *       }
 *   }
 * }
 */
data class Newsfeed(
    @SerializedName("kind") val kind: String = "",
    @SerializedName("data") val data: NewsfeedData = NewsfeedData("", ArrayList(0))
) {
    fun length(): Int = data.children.size
}

data class NewsfeedData(
    @SerializedName("after") val after: String,
    @SerializedName("children") val children: List<NewsItem>
)