package com.damakable.kotlinnews.model

import com.damakable.kotlinnews.api.NewsfeedService
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsfeedProviderTest {
    @Mock
    lateinit var newsfeedService: NewsfeedService

    private val testDispatcher = TestCoroutineDispatcher()

    private val newsfeed = Newsfeed("feed",
        NewsfeedData("after",
            MutableList(1) { i -> NewsItem("t3",
                NewsItemData(i.toString(), "selftext", "thumbnail"))
        }))

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test fun `requestFeed provides Newsfeed to callback on success`() = runBlockingTest {
        whenever(newsfeedService.getNewsfeed()).thenReturn(Response.success(newsfeed))

        var pass = false
        NewsfeedProvider(newsfeedService).requestFeed({
            assertNotNull(it)
            assertEquals("feed", it.kind)
            pass = true
        }, {
            fail()
        })

        verify(newsfeedService).getNewsfeed()
        assertTrue(pass)
    }

    @Test fun `requestFeed provides Exception to callback on failure`() = runBlockingTest {
        whenever(newsfeedService.getNewsfeed())
            .thenReturn(Response.error(404, mock(ResponseBody::class.java)))

        var pass = false
        NewsfeedProvider(newsfeedService).requestFeed({
            fail()
        }, {
            assertNotNull(it)
            pass = true
        })

        verify(newsfeedService).getNewsfeed()
        assertTrue(pass)
    }
}
