package com.damakable.kotlinnews.screens.feed

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class EndlessScrollListenerTest {
    @MockK lateinit var presenter: NewsfeedPresenter
    @MockK lateinit var feedAdapter: NewsfeedAdapter

    lateinit var recyclerView: RecyclerView
    lateinit var listener: EndlessScrollListener

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { feedAdapter.hasStableIds() } returns true

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val layoutManager = LinearLayoutManager(appContext)
        recyclerView = RecyclerView(appContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = feedAdapter
        listener = EndlessScrollListener(layoutManager, presenter)
    }

    @Test
    fun loadNextPageOnScroll_AboveThreshold() {
        every { feedAdapter.itemCount } returns 3

        listener.onScrolled(recyclerView, 0, 0)

        verify { presenter.loadNextPage() }
        confirmVerified(presenter)
    }

    @Test
    fun doNotLoadNextPageOnScroll_BelowThreshold() {
        every { feedAdapter.itemCount } returns 20

        listener.onScrolled(recyclerView, 0, 0)

        verify(exactly = 0) { presenter.loadNextPage() }
        confirmVerified(presenter)
    }
}
