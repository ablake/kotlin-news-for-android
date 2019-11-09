package com.damakable.kotlinnews.model

import com.damakable.kotlinnews.api.NewsfeedService
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.fail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test fun `requestFeed provides Newsfeed to observer on success`() = runBlockingTest {
        Mockito.`when`(newsfeedService.getNewsfeed()).thenReturn(Response.success(Newsfeed()))

        NewsfeedProvider(newsfeedService).requestFeed({
            assertNotNull(it)
        }, {
            fail()
        })

        verify(newsfeedService).getNewsfeed()
    }

    @Test fun `requestFeed provides Exception to observer on failure`() = runBlockingTest {
        Mockito.`when`(newsfeedService.getNewsfeed())
            .thenReturn(Response.error(404, mock(ResponseBody::class.java)))

        NewsfeedProvider(newsfeedService).requestFeed({
            fail()
        }, {
            assertNotNull(it)
        })

        verify(newsfeedService).getNewsfeed()
    }
}
