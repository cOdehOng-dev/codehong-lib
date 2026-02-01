package com.codehong.library.widget.tab.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.codehong.library.widget.databinding.HonglibItemScrollTabBinding
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongMargin
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.def.HongTextBuilder

class HongTabScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {

    private val container = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    var option = HongTabScrollOption()
        private set

    private var selectedIndex: Int = 0

    init {
        isHorizontalScrollBarEnabled = false
        addView(container, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT))
    }

    fun set(option: HongTabScrollOption): HongTabScrollView {
        this.option = option
        this.selectedIndex = option.initialSelectIndex

        drawTabs()
        post { scrollToSelectedTab(animated = false) }

        return this
    }

    private fun drawTabs() {
        container.removeAllViews()

        container.setLayout(option.width, option.height)?.apply {
            leftMargin = context.dpToPx(option.margin.left)
            topMargin = context.dpToPx(option.margin.top)
            rightMargin = context.dpToPx(option.margin.right)
            bottomMargin = context.dpToPx(option.margin.bottom)
        }
        container.hongPadding(option.padding)

        option.tabList.forEachIndexed { index, _ ->
            container.addView(createTabView(index))
        }
    }

    private fun createTabView(index: Int): View {
        val isSelected = selectedIndex == index
        val isFirst = index == 0
        val isLast = index == option.tabList.size - 1
        val tabTitle = option.tabTextList.getOrElse(index) { "" }

        val itemBinding = HonglibItemScrollTabBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )

        setupTabContainer(itemBinding, index, isSelected, isFirst, isLast)
        setupTabText(itemBinding, tabTitle, isSelected)

        return itemBinding.root
    }

    private fun setupTabContainer(
        binding: HonglibItemScrollTabBinding,
        index: Int,
        isSelected: Boolean,
        isFirst: Boolean,
        isLast: Boolean
    ) {
        val backgroundColor = if (isSelected) {
            option.selectBackgroundColorHex
        } else {
            option.unselectBackgroundColorHex
        }

        val borderInfo = HongBorderInfo(
            color = if (isSelected) option.selectBorderColorHex else option.unselectBorderColorHex,
            width = if (isSelected) option.selectBorderWidth else option.unselectBorderWidth
        )

        val marginInfo = HongSpacingInfo(
            left = if (isFirst) 0f else (option.tabBetweenPadding / 2).toFloat(),
            right = if (isLast) 0f else (option.tabBetweenPadding / 2).toFloat()
        )

        val paddingInfo = HongSpacingInfo(
            left = option.tabTextHorizontalPadding.toFloat(),
            top = option.tabTextVerticalPadding.toFloat(),
            right = option.tabTextHorizontalPadding.toFloat(),
            bottom = option.tabTextVerticalPadding.toFloat()
        )

        with(binding.flContainer) {
            hongBackground(
                backgroundColor = backgroundColor,
                border = borderInfo,
                radius = option.radius
            )
            hongMargin(marginInfo)
            hongPadding(paddingInfo)
            setOnClickListener { onTabClicked(index) }
        }
    }

    private fun setupTabText(
        binding: HonglibItemScrollTabBinding,
        tabTitle: String,
        isSelected: Boolean
    ) {
        binding.vDummyTab.set(
            option = HongTextBuilder()
                .text(tabTitle)
                .typography(option.selectTabTextTypo)
                .applyOption()
        )

        val textOption = if (isSelected) {
            HongTextBuilder()
                .text(tabTitle)
                .typography(option.selectTabTextTypo)
                .color(option.selectTabTextColorHex)
                .applyOption()
        } else {
            HongTextBuilder()
                .text(tabTitle)
                .typography(option.unselectTabTextTypo)
                .color(option.unselectTabTextColorHex)
                .applyOption()
        }

        binding.vTab.set(option = textOption)
    }

    private fun onTabClicked(index: Int) {
        if (selectedIndex == index) return

        selectedIndex = index
        drawTabs()
        scrollToSelectedTab(animated = true)
        option.tabList.getOrNull(index)?.let { item ->
            option.tabClick?.invoke(index, item)
        }
    }

    private fun scrollToSelectedTab(animated: Boolean) {
        val tabView = container.getChildAt(selectedIndex) ?: return

        tabView.postDelayed({
            val scrollX = tabView.left - (width - tabView.width) / 2
            if (animated) {
                smoothScrollTo(scrollX, 0)
            } else {
                scrollTo(scrollX, 0)
            }
        }, SCROLL_DELAY_MS)
    }

    companion object {
        private const val SCROLL_DELAY_MS = 100L
    }
}
