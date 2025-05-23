package org.wikipedia.readinglist

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import org.wikipedia.R
import org.wikipedia.databinding.ViewReadingListHeaderBinding
import org.wikipedia.readinglist.database.ReadingList
import org.wikipedia.util.GradientUtil
import org.wikipedia.util.ResourceUtil
import org.wikipedia.views.ViewUtil

class ReadingListHeaderView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private val binding = ViewReadingListHeaderBinding.inflate(LayoutInflater.from(context), this)
    private var imageViews = listOf(binding.readingListHeaderImage0, binding.readingListHeaderImage1, binding.readingListHeaderImage2,
            binding.readingListHeaderImage3, binding.readingListHeaderImage4, binding.readingListHeaderImage5)
    private var readingList: ReadingList? = null

    init {
        if (!isInEditMode) {
            layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            binding.readingListHeaderImageGradient.background = GradientUtil.getPowerGradient(
                ResourceUtil.getThemedColor(context, R.attr.overlay_color), Gravity.TOP)
            clearThumbnails()
        }
    }

    fun setReadingList(readingList: ReadingList) {
        this.readingList = readingList
        if (readingList.pages.isEmpty()) {
            binding.readingListHeaderImageContainer.visibility = GONE
            binding.readingListHeaderEmptyImage.visibility = VISIBLE
        } else {
            binding.readingListHeaderImageContainer.visibility = VISIBLE
            binding.readingListHeaderEmptyImage.visibility = GONE
            updateThumbnails()
        }
    }

    private fun clearThumbnails() {
        imageViews.forEach {
            ViewUtil.loadImage(it, null)
        }
    }

    private fun updateThumbnails() {
        readingList?.let {
            clearThumbnails()
            val thumbUrls = it.pages.mapNotNull { page -> page.thumbUrl }
                .filterNot { url -> url.isEmpty() }
            (imageViews zip thumbUrls).forEach { (imageView, url) ->
                ViewUtil.loadImage(imageView, url)
            }
        }
    }
}
