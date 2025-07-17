package com.codehong.library.widget.tab

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.codehong.library.widget.databinding.HonglibItemScrollTabBinding
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongMargin
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout

class HongScrollTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {

    private val container = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    var option = HongScrollTabOption()
        private set

    private var selectedIndex: Int = 0
    private var tabList: List<Any> = emptyList()
    private var tabTitleList: List<String> = emptyList()
    private var onTabClick: ((Int, Any) -> Unit)? = null

    init {
        isHorizontalScrollBarEnabled = false
        addView(container, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT))
    }

    fun set(
        option: HongScrollTabOption,
        onTabClick: (Int, Any) -> Unit
    ): HongScrollTabView {
        this.option = option
        this.onTabClick = onTabClick
        this.tabList = option.tabList
        this.tabTitleList = option.tabTitleList
        this.selectedIndex = option.initialSelectIndex

        drawTabs()
        post { scrollToSelectedTab(animated = false) }

        return this
    }

    private fun drawTabs() {
        container.removeAllViews()

        with(container) {
            removeAllViews()
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
        }

        tabList.forEachIndexed { index, item ->
            val tabView = createTabView(index)
            container.addView(tabView)
        }
    }

    private fun createTabView(index: Int): View {
        val isSelected = selectedIndex == index
        val tabTitle = tabTitleList.getOrNull(index) ?: ""

        val itemBinding =
            HonglibItemScrollTabBinding.inflate(LayoutInflater.from(context), this, false)

        with(itemBinding.flContainer) {
            hongBackground(
                backgroundColor = if (isSelected) {
                    option.selectBackgroundColor
                } else {
                    option.unselectBackgroundColor
                },
                border = HongBorderInfo(
                    color = if (isSelected) {
                        option.selectBorderColor
                    } else {
                        option.unselectBorderColor
                    },
                    width = option.borderWidth,
                ),
                radius = option.radius,
            )
            hongMargin(
                HongSpacingInfo(
                    left = if (index == 0) {
                        0f
                    } else {
                        (option.tabBetweenPadding / 2).toFloat()
                    },
                    right = if (index == option.tabList.size - 1) {
                        0f
                    } else {
                        (option.tabBetweenPadding / 2).toFloat()
                    }
                )
            )
            hongPadding(
                padding = HongSpacingInfo(
                    left = option.tabTextHorizontalPadding.toFloat(),
                    top = option.tabTextVerticalPadding.toFloat(),
                    right = option.tabTextHorizontalPadding.toFloat(),
                    bottom = option.tabTextVerticalPadding.toFloat()
                )
            )

            setOnClickListener {
                if (selectedIndex != index) {
                    selectedIndex = index
                    drawTabs()
                    if (!isSelected) {
                        scrollToSelectedTab(animated = true)
                    }
                    onTabClick?.invoke(index, tabList[index])
                }
            }
        }


        itemBinding.vDummyTab.set(
            option = HongTextBuilder()
                .copy(option.selectTabTextOption)
                .text(tabTitle)
                .applyOption()
        )

        itemBinding.vTab.set(
            option = if (isSelected) {
                HongTextBuilder()
                    .copy(option.selectTabTextOption)
                    .text(tabTitle)
                    .applyOption()
            } else {
                HongTextBuilder()
                    .copy(option.unselectTabTextOption)
                    .text(tabTitle)
                    .applyOption()
            }
        )

        return itemBinding.root
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
        }, 100)
    }
}
