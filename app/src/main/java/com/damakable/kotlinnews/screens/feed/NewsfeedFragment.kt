package com.damakable.kotlinnews.screens.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.damakable.kotlinnews.MainActivity
import com.damakable.kotlinnews.R
import com.damakable.kotlinnews.model.NewsItem
import com.damakable.kotlinnews.model.NewsfeedProvider
import kotlinx.android.synthetic.main.fragment_newsfeed.*

class NewsfeedFragment : Fragment(R.layout.fragment_newsfeed), NewsfeedView {
    private lateinit var adapter: NewsfeedAdapter
    private lateinit var presenter: NewsfeedPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val newsfeedService = (activity as MainActivity).newsfeedService
        presenter = NewsfeedPresenter(NewsfeedProvider(newsfeedService), this)
    }

    override fun onStart() {
        super.onStart()

        if (!::adapter.isInitialized)
            adapter = NewsfeedAdapter(findNavController())

        newsfeed_recycler.layoutManager = LinearLayoutManager(context)
        newsfeed_recycler.adapter = adapter
        newsfeed_recycler.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

        presenter.requestFeedIfEmpty()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.app_name)
    }

    override fun clear() = adapter.clear()

    override fun addItems(newsItems: List<NewsItem>) = adapter.addItems(newsItems)

    override fun isEmpty(): Boolean = adapter.itemCount == 0

    override fun displayError(error: Exception) {
        Log.d("Newsfeed", error.toString())
    }
}