package com.damakable.kotlinnews.model

import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsfeedProviderTest {
//    @Mock
//    lateinit var newsfeedService: NewsFeedService

    @Test fun `requestFeed provides Newsfeed to observer on success`() {
        NewsfeedProvider().requestFeed({
            assertNotNull(it)
        }, {
            fail()
        })
    }

    @Test fun `requestFeed provides ErrorInfo to observer on failure`() {
        NewsfeedProvider().requestFeed({
            fail()
        }, {
            assertNotNull(it)
        })
    }
}