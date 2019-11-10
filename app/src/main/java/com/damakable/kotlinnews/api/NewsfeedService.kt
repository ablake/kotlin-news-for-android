package com.damakable.kotlinnews.api

import com.damakable.kotlinnews.model.Newsfeed
import retrofit2.Response
import retrofit2.http.GET

interface NewsfeedService {
    @GET(".json")
    suspend fun getNewsfeed(): Response<Newsfeed>
}
