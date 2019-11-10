package com.damakable.kotlinnews.screens.feed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.damakable.kotlinnews.BuildConfig
import com.damakable.kotlinnews.R
import com.damakable.kotlinnews.api.NewsfeedService
import com.damakable.kotlinnews.model.NewsItem
import com.damakable.kotlinnews.model.NewsfeedProvider
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_newsfeed.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsfeedFragment : Fragment(R.layout.fragment_newsfeed), NewsfeedView {

    lateinit var adapter: NewsfeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Move all this dependency creation into a factory or Dagger component
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

        newsfeed_recycler.layoutManager = LinearLayoutManager(context)
        adapter = NewsfeedAdapter(findNavController())
        newsfeed_recycler.adapter = adapter

        val presenter = NewsfeedPresenter(NewsfeedProvider(newsfeedService), this)
        presenter.refresh()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.app_name)
    }

    override fun clear() {
        adapter.clear()
    }

    override fun addItems(newsItems: List<NewsItem>) {
        adapter.addItems(newsItems)
    }

    override fun displayError(error: Exception) {
        Log.d("Newsfeed", error.toString())
    }
}