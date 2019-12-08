package com.damakable.kotlinnews.api

import com.damakable.kotlinnews.model.Newsfeed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsfeedService {
    @GET(".json")
    suspend fun getNewsfeed(): Response<Newsfeed>

    @GET(".json")
    suspend fun getPage(@Query("after") after: String): Response<Newsfeed>
}
