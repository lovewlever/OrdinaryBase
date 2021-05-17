package com.ordinary.basis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ordinary.basis.R
import com.ordinary.basis.databinding.ItemPagingStateFooterBinding

class LoadStateFooterRecyclerAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<LoadStateFooterRecyclerHolder>() {

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error || loadState is LoadState.NotLoading
    }

    override fun onBindViewHolder(holder: LoadStateFooterRecyclerHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateFooterRecyclerHolder =
        LoadStateFooterRecyclerHolder(parent, retry)
}


class LoadStateFooterRecyclerHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_paging_state_footer, parent, false)
) {

    private val binding = ItemPagingStateFooterBinding.bind(itemView)
    private val atvRetry = binding.atvRetry.also {
        it.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                binding.atvTextView.text = loadState.error.localizedMessage
            }
            is LoadState.NotLoading -> {
                binding.atvTextView.text = "没有更多数据"
            }
            else -> {
                binding.atvTextView.text = "加载中..."
            }
        }

        binding.progressBar.isVisible = loadState is LoadState.Loading
        atvRetry.isVisible = loadState is LoadState.Error
        binding.atvTextView.isVisible = loadState is LoadState.Error
                || loadState is LoadState.Loading
                || loadState is LoadState.NotLoading
    }
}