package com.damakable.kotlinnews.screens.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.damakable.kotlinnews.R
import com.damakable.kotlinnews.glide.GlideApp
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.fragment_news_item.*

class NewsItemFragment : Fragment(R.layout.fragment_news_item) {
    private val args: NewsItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val thumbnail = args.item.thumbnail()
        if (thumbnail.isNotEmpty())
            GlideApp.with(view.context)
                .load(args.item.thumbnail())
                .into(item_image)
        else
            item_image.visibility = View.GONE

        val markwon = Markwon.create(view.context)
        markwon.setMarkdown(item_body, args.item.body())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = args.item.title()
    }
}