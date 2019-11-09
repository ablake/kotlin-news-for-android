package com.damakable.kotlinnews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.damakable.kotlinnews.model.NewsfeedProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NewsfeedProvider().requestFeed({
            Log.d("Newsfeed", it.toString())
        }, {
            Log.d("Newsfeed", it.toString())
        })
    }
}
