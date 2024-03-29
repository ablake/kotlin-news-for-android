package com.damakable.kotlinnews.screens.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.damakable.kotlinnews.R
import com.damakable.kotlinnews.glide.GlideApp
import com.damakable.kotlinnews.model.NewsItem
import com.damakable.kotlinnews.model.Newsfeed
import kotlinx.android.synthetic.main.newsfeed_item_view.view.*

class NewsfeedAdapter(private val navController: NavController)
    : RecyclerView.Adapter<NewsfeedAdapter.NewsfeedViewHolder>() {
    private var newsfeed = Newsfeed()

    class NewsfeedViewHolder(
        itemView: View,
        private val navController: NavController
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var newsItem: NewsItem? = null
            set(value) {
                field = value
                itemView.newsfeed_item_title.text = value?.title()
            }

        init { itemView.setOnClickListener(this) }

        override fun onClick(view: View?) {
                        val bundle = Bundle()
            bundle.putParcelable("item", newsItem)
            navController.navigate(R.id.action_newsfeed_to_item, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsfeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.newsfeed_item_view, parent, false)
        return NewsfeedViewHolder(view, navController)
    }

    override fun onBindViewHolder(holder: NewsfeedViewHolder, position: Int) {
        val item = newsfeed.data.children[position]
        holder.newsItem = item
        holder.itemView.newsfeed_item_title.text = item.title()

        val thumbnail = item.thumbnail()
        if (thumbnail.isNotEmpty())
            GlideApp.with(holder.itemView.context)
                    .load(thumbnail)
                    .into(holder.itemView.newsfeed_item_image)
        else
            holder.itemView.newsfeed_item_image.visibility = View.GONE
    }

    override fun getItemCount() = newsfeed.length()

    fun clear() {
        newsfeed.clear()
        notifyDataSetChanged()
    }

    fun addItems(newsItems: List<NewsItem>) {
        newsfeed.add(newsItems)
        notifyDataSetChanged()
    }
}