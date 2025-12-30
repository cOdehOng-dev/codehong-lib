package com.codehong.library.widget.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.codehong.library.widget.calendar.adapter.HongCalendarListAdapter
import com.codehong.library.widget.databinding.HonglibViewCalendarBinding
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextView
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.extensions.toParseColor
import org.threeten.bp.LocalDate

class HongCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        HonglibViewCalendarBinding.inflate(LayoutInflater.from(context), this, true)

    var option = HongCalendarOption()
        private set

    fun set(
        option: HongCalendarOption
    ): HongCalendarView {
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

        // 요일 헤더
        if (binding.llDayOfWeek.childCount > 0) {
            binding.llDayOfWeek.removeAllViews()
        }

        option.dayOfWeekLangType.dayOfWeekList.forEach { day ->
            binding.llDayOfWeek.addView(
                FrameLayout(context).apply {
                    layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                        weight = 1f
                    }

                    val textView = HongTextView(context).set(
                        HongTextBuilder()
                            .copy(option.dayOfWeekTextOption)
                            .text(day)
                            .applyOption()
                    )

                    val params = FrameLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                    ).apply {
                        gravity = Gravity.CENTER
                    }

                    addView(textView, params)
                }
            )
        }

        // 요일 헤더 라인
        if (option.dayOfWeekBottomLineColorHex != HongColor.TRANSPARENT.hex) {
            binding.vDayOfWeekDivider.setBackgroundColor(option.dayOfWeekBottomLineColorHex.toParseColor())
            binding.vDayOfWeekDivider.visibility = View.VISIBLE
        } else {
            binding.vDayOfWeekDivider.visibility = View.GONE
        }
        
        option.bottomSpacingDayOfWeek

        val today = LocalDate.now()
        val maxYearLater = today.plusYears(option.maxYears.toLong())
        val months = (0..12).map { today.plusMonths(it.toLong()).withDayOfMonth(1) }
            .filter { it <= maxYearLater }


        binding.rvMonthsDays.apply {
            adapter = HongCalendarListAdapter(
                option = option,
                today = today,
            ).apply {
                this.submitList(months)
            }
            hongPadding(
                HongSpacingInfo(
                    top = option.bottomSpacingDayOfWeek.toFloat()
                )
            )
        }
        return this
    }
}