package com.damakable.kotlinnews.model

class NewsfeedProvider {
    fun requestFeed(onSuccess: (Newsfeed) -> Unit,
                    onFailure: (ErrorInfo) -> Unit) {
        onSuccess(Newsfeed())
    }
}