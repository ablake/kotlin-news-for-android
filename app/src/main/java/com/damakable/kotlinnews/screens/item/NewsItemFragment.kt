package com.damakable.kotlinnews.screens.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.damakable.kotlinnews.R
import kotlinx.android.synthetic.main.fragment_news_item.*

class NewsItemFragment : Fragment(R.layout.fragment_news_item) {
    private val args: NewsItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = args.item.title()
        item_body.text = args.item.body()
    }
}