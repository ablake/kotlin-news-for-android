package com.damakable.kotlinnews.screens.feed

import com.damakable.kotlinnews.model.NewsItem

interface NewsfeedView {
    fun clear()

    fun addItems(newsItems: List<NewsItem>)

    fun displayError(error: Exception)
}