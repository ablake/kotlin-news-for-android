package com.damakable.kotlinnews.api

import com.damakable.kotlinnews.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceProvider {
    companion object {
//        private val BASE_URL = "https://www.reddit.com/r/kotlin/"
        private const val BASE_URL = "https://www.reddit.com/r/awww/"
    }

    fun getNewsfeedService(): NewsfeedService {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(loggingInterceptor)
        }

        val httpClient = builder.build()
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

        return retrofit.create(NewsfeedService::class.java)
    }
}