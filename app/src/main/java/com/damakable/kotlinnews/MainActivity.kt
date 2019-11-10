package com.damakable.kotlinnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.damakable.kotlinnews.api.NewsfeedService
import com.damakable.kotlinnews.api.ServiceProvider

class MainActivity : AppCompatActivity() {

    lateinit var newsfeedService: NewsfeedService
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsfeedService = ServiceProvider().getNewsfeedService()
    }
}
