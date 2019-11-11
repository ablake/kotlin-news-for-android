package com.damakable.kotlinnews.screens.feed

import com.damakable.kotlinnews.model.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsfeedPresenterTest {
    @Mock lateinit var feedProvider: NewsfeedProvider
    @Mock lateinit var view: NewsfeedView

    private fun create() = NewsfeedPresenter(feedProvider, view)

    @Test fun `request feed when view is empty`() {
        val presenter = create()
        whenever(view.isEmpty()).thenReturn(true)
        presenter.requestFeedIfEmpty()
        verify(feedProvider).requestFeed(any(), any())
    }

    @Test fun `don't request feed when already loaded`() {
        val presenter = create()
        whenever(view.isEmpty()).thenReturn(false)
        presenter.requestFeedIfEmpty()
        verify(feedProvider, never()).requestFeed(any(), any())
    }

    @Test fun `add items to view when page retrieved`() {
        val presenter = create()
        whenever(view.isEmpty()).thenReturn(true)
        presenter.requestFeedIfEmpty()

        val items = MutableList(0) { i -> NewsItem("t3",
            NewsItemData(i.toString(), "selftext", "thumbnail")) }
        val newsfeed = Newsfeed("feed", NewsfeedData("after", items))

        argumentCaptor<(Newsfeed) -> Unit>().apply {
            verify(feedProvider).requestFeed(capture(), any())
            firstValue.invoke(newsfeed)
        }
        verify(view).addItems(items)
    }

    @Test fun `display error when request fails`() {
        val presenter = create()
        whenever(view.isEmpty()).thenReturn(true)
        presenter.requestFeedIfEmpty()

        val exception = Exception()
        argumentCaptor<(Exception) -> Unit>().apply {
            verify(feedProvider).requestFeed(any(), capture())
            firstValue.invoke(exception)
        }
        verify(view).displayError(exception)
    }
}
