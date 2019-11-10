package com.damakable.kotlinnews.screens.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damakable.kotlinnews.R
import com.damakable.kotlinnews.model.NewsItem
import com.damakable.kotlinnews.model.NewsItemData
import kotlinx.android.synthetic.main.fragment_newsfeed.*

class NewsfeedFragment : Fragment(R.layout.fragment_newsfeed) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_item_button.setOnClickListener {
            val newsItem = NewsItem("t3",
                NewsItemData("title", "selftext", "thumbnail")
            )
            val bundle = Bundle()
            bundle.putParcelable("item", newsItem)
            findNavController().navigate(R.id.action_newsfeed_to_item, bundle)
        }
    }
}