package com.damakable.kotlinnews.screens.feed

import com.damakable.kotlinnews.model.Newsfeed
import com.damakable.kotlinnews.model.NewsfeedProvider

class NewsfeedPresenter(
    private val feedProvider: NewsfeedProvider,
    private val view: NewsfeedView
) {
    private var after = ""

    fun refresh() {
        view.clear()
        feedProvider.requestFeed(::onPageRetrieved, view::displayError)
    }

//    fun loadNext() {
//        feedProvider.requestPage(after, ::onPageRetrieved, view::displayError)
//    }

    private fun onPageRetrieved(page: Newsfeed) {
        view.addItems(page.items())
        after = page.after()
    }
}