package com.codehong.library.widget.pager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codehong.library.widget.databinding.HonglibItemHorizontalPagerBinding

class HongHorizontalPagerAdapter : RecyclerView.Adapter<HongHorizontalPagerAdapter.HorizontalPagerViewHolder>() {

    private var pageInfoList: List<Any>? = null
    private var binder: ((HonglibItemHorizontalPagerBinding, Any) -> Unit)? = null

    fun submitList(
        pageInfoList: List<Any>?,
        binder: ((HonglibItemHorizontalPagerBinding, Any) -> Unit)?
    ) {
        this.pageInfoList = pageInfoList
        this.binder = binder
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalPagerViewHolder {
        return HorizontalPagerViewHolder(
            HonglibItemHorizontalPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalPagerViewHolder, position: Int) {
        pageInfoList?.getOrNull(position)?.let {
            binder?.invoke(holder.binding, it)
        }
    }

    override fun getItemCount(): Int = pageInfoList?.size ?: 0

    open class HorizontalPagerViewHolder(
        val binding: HonglibItemHorizontalPagerBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
