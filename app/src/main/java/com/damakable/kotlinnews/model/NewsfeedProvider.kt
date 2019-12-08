package com.damakable.kotlinnews.model

import com.damakable.kotlinnews.api.NewsfeedService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsfeedProvider(private val newsfeedService: NewsfeedService) {

    private val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    fun requestFeed(onSuccess: (Newsfeed) -> Unit,
                    onFailure: (Exception) -> Unit) = scope.launch {
        try {
            handleResponse(onSuccess, onFailure, newsfeedService.getNewsfeed())
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    fun requestPage(onSuccess: (Newsfeed) -> Unit,
                    onFailure: (Exception) -> Unit,
                    after: String) = scope.launch {
        try {
            handleResponse(onSuccess, onFailure, newsfeedService.getPage(after))
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    private fun handleResponse(onSuccess: (Newsfeed) -> Unit,
                               onFailure: (Exception) -> Unit,
                               response: Response<Newsfeed>) {
        if (response.isSuccessful) {
            onSuccess(response.body() ?: Newsfeed())
        } else {
            val error = "Error code: " + response.code()
            onFailure(Exception(error))
        }
    }
}