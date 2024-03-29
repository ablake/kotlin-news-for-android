package com.damakable.kotlinnews.screens.feed

import com.damakable.kotlinnews.model.Newsfeed
import com.damakable.kotlinnews.model.NewsfeedProvider

class NewsfeedPresenter(
    private val feedProvider: NewsfeedProvider,
    private val view: NewsfeedView
) {
    private var after = ""

    // TODO: Stretch goal: swipe-to-refresh
//    fun refresh() {
//        view.clear()
//        requestFeedIfEmpty()
//    }

    fun requestFeedIfEmpty() {
        if (view.isEmpty())
            feedProvider.requestFeed(::onPageRetrieved, view::displayError)
    }

    fun loadNextPage() {
        feedProvider.requestPage(::onPageRetrieved, view::displayError, after)
    }

    private fun onPageRetrieved(page: Newsfeed) {
        view.addItems(page.items())
        after = page.after()
    }
}
