package com.damakable.kotlinnews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.damakable.kotlinnews.api.NewsfeedService
import com.damakable.kotlinnews.model.NewsfeedProvider
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(loggingInterceptor)
        }

        val httpClient = builder.build()
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/r/kotlin/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

        val newsfeedService = retrofit.create(NewsfeedService::class.java)

        NewsfeedProvider(newsfeedService).requestFeed({ newsfeed ->
            newsfeed?.newsItems()?.forEach {
                Log.d("Newsfeed", it.data.title)
            }
        }, {
            Log.d("Newsfeed", it.toString())
        })
    }
}
