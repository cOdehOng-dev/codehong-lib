package com.codehong.library.widget.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codehong.library.widget.calendar.HongCalendarOption
import com.codehong.library.widget.databinding.HonglibItemCalendarBinding
import org.threeten.bp.LocalDate

class HongCalendarListAdapter constructor(
    private val option: HongCalendarOption,
    private val today: LocalDate,
) : ListAdapter<LocalDate, HongCalendarViewHolder>(
    object : DiffUtil.ItemCallback<LocalDate>() {
        override fun areItemsTheSame(
            oldItem: LocalDate,
            newItem: LocalDate
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: LocalDate,
            newItem: LocalDate
        ): Boolean {
            return oldItem.equals(newItem)
        }
    }
) {

    private var startDate: LocalDate? = option.initialSelectedInfo?.getStartLocalDate()
    private var endDate: LocalDate? = option.initialSelectedInfo?.getEndLocalDate()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HongCalendarViewHolder {
        return HongCalendarViewHolder(
            HonglibItemCalendarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            option
        )
    }

    override fun onBindViewHolder(holder: HongCalendarViewHolder, position: Int) {
        if (position == RecyclerView.NO_POSITION) return
        val pos = holder.adapterPosition
        val item = getItem(pos)

        holder.bind(
            month = item,
            today = today,
            startDate = startDate,
            endDate = endDate,
            onDayClick = { clickedDate ->
                if (startDate == null || endDate != null) {
                    startDate = clickedDate
                    endDate = null
                } else if (clickedDate < startDate) {
                    startDate = clickedDate
                } else {
                    endDate = clickedDate
                }
                notifyDataSetChanged()
                option.onSelected?.invoke(startDate, endDate)
            }
        )
    }
}