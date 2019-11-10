package com.damakable.kotlinnews.model

import com.damakable.kotlinnews.api.NewsfeedService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsfeedProvider(private val newsfeedService: NewsfeedService) {

    private val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    fun requestFeed(onSuccess: (Newsfeed?) -> Unit,
                    onFailure: (Exception) -> Unit) = scope.launch {
        try {
            val response = newsfeedService.getNewsfeed()
            if (response.isSuccessful) {
                onSuccess(response.body())
            } else {
                val error = "Error code: " + response.code()
                onFailure(Exception(error))
            }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}