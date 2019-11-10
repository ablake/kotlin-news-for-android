package com.damakable.kotlinnews.screens.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damakable.kotlinnews.R
import com.damakable.kotlinnews.model.Newsfeed
import kotlinx.android.synthetic.main.newsfeed_item_view.view.*

class NewsfeedAdapter : RecyclerView.Adapter<NewsfeedAdapter.NewsfeedViewHolder>() {
    var newsfeed = Newsfeed()

    class NewsfeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsfeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.newsfeed_item_view, parent, false)
        return NewsfeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsfeedViewHolder, position: Int) {
        holder.itemView.newsfeed_item_title.text = newsfeed.data.children[position].title()
    }

    override fun getItemCount(): Int {
        return newsfeed.length()
    }
}