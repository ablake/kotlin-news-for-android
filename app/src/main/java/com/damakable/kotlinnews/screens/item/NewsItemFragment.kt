package com.damakable.kotlinnews.screens.item

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.damakable.kotlinnews.R

class NewsItemFragment : Fragment(R.layout.fragment_news_item) {
    val args: NewsItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("NewsItem", args.item.data?.title)
    }
}