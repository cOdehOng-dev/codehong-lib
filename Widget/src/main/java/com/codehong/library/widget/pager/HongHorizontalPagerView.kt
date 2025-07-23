package com.codehong.library.widget.pager

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.codehong.library.widget.databinding.HonglibHorizontalPagerViewBinding
import com.codehong.library.widget.databinding.HonglibItemHorizontalPagerBinding
import com.codehong.library.widget.pager.adapter.HongHorizontalPagerAdapter
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout

class HongHorizontalPagerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding =
        HonglibHorizontalPagerViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var option = HongHorizontalPagerOption()

    private val handler = Handler(Looper.getMainLooper())

    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val next =
                (binding.vpHorizontal.currentItem + 1) % (binding.vpHorizontal.adapter?.itemCount ?: 1)
            binding.vpHorizontal.setCurrentItem(next, true)
            handler.postDelayed(this, option.autoScrollMillSecond)
        }
    }

    private val adapter = HongHorizontalPagerAdapter()

    init {
        binding.vpHorizontal.adapter = adapter
        binding.vpHorizontal.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val originalIndex =
                    position % ((option.pageInfoList ?: emptyList()).size.takeIf { it > 0 } ?: 1)
                option.currentIndex.invoke(originalIndex)
            }
        })
    }

    fun set(
        option: HongHorizontalPagerOption,
        binder: ((HonglibItemHorizontalPagerBinding, Any) -> Unit)?
    ): HongHorizontalPagerView {
        this.option = option

        setLayout(
            option.width,
            option.height
        )?.apply {
            this.leftMargin = context.dpToPx(option.margin.left)
            this.topMargin = context.dpToPx(option.margin.top)
            this.rightMargin = context.dpToPx(option.margin.right)
            this.bottomMargin = context.dpToPx(option.margin.bottom)
        }

        hongPadding(option.padding)
        hongBackground(backgroundColor = option.backgroundColorHex)

        val pageList = option.pageInfoList ?: emptyList()
        val finalList = when {
            option.infiniteScroll.second -> List(10) { pageList }.flatten()
            option.infiniteScroll.first -> List(10) { pageList }.flatten()
            else -> pageList
        }

        adapter.submitList(finalList, binder)

        // ✅ 페이지 간 간격
        binding.vpHorizontal.setPageTransformer(
            MarginPageTransformer(context.dpToPx(option.pageSpacing))
        )

        if (option.autoScrollMillSecond > 0) {
            handler.postDelayed(autoScrollRunnable, option.autoScrollMillSecond)
        }

        // ✅ ViewPager2 양옆 미리 보기 (Compose의 contentPadding)
        val recyclerView = binding.vpHorizontal.getChildAt(0)
        if (recyclerView is RecyclerView) {
            val leftPaddingPx = context.dpToPx(option.prevPageVisibleWidth)
            val rightPaddingPx = context.dpToPx(option.nextPageVisibleWidth)
            recyclerView.setPadding(leftPaddingPx, 0, rightPaddingPx, 0)
            recyclerView.clipToPadding = false
        }

        return this
    }

    fun stopAutoScroll() {
        handler.removeCallbacks(autoScrollRunnable)
    }
}
