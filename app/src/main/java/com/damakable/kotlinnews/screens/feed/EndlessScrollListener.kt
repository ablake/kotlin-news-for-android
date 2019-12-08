package com.damakable.kotlinnews.screens.feed

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * An OnScrollListener that notifies a presenter when its RecyclerView is near the bottom
 * of its list.
 *
 * Based on this example: https://gist.github.com/nesquena/d09dc68ff07e845cc622
 */
class EndlessScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val presenter: NewsfeedPresenter
) : RecyclerView.OnScrollListener() {

    private var loading: Boolean = false
    private var previousCount: Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val itemCount = layoutManager.itemCount

        if (itemCount < previousCount) {
            previousCount = itemCount
        }

        if (loading && itemCount > previousCount) {
            loading = false
            previousCount = itemCount
        }

        if (!loading && lastVisibleItemPosition + VISIBLE_THRESHOLD > itemCount) {
            presenter.loadNextPage()
            loading = true
        }
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
}
