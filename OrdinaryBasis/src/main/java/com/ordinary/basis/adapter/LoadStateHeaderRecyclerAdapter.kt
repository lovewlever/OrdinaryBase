package com.ordinary.basis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ordinary.basis.R
import com.ordinary.basis.databinding.ItemPagingStateFooterBinding
import com.ordinary.basis.databinding.ItemPagingStateHeaderBinding

class LoadStateHeaderRecyclerAdapter(
    private val refresh: () -> Unit
): LoadStateAdapter<LoadStateHeaderRecyclerHolder>() {

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error || loadState is LoadState.NotLoading
    }

    override fun onBindViewHolder(holder: LoadStateHeaderRecyclerHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateHeaderRecyclerHolder =
        LoadStateHeaderRecyclerHolder(parent, refresh)
}


class LoadStateHeaderRecyclerHolder(
    parent: ViewGroup,
    refresh: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_paging_state_header, parent, false)
) {

    private val binding = ItemPagingStateHeaderBinding.bind(itemView)

    fun bind(loadState: LoadState) {
    }
}