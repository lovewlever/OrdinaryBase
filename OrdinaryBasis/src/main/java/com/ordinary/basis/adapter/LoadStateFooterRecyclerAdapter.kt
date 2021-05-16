package com.ordinary.basis.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadStateFooterRecyclerAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<LoadStateFooterRecyclerHolder>() {

    override fun onBindViewHolder(holder: LoadStateFooterRecyclerHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateFooterRecyclerHolder =
        LoadStateFooterRecyclerHolder(parent, retry)
}