package com.ordinary.basis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ordinary.basis.R

class LoadStateFooterRecyclerHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.dialog_loading, parent, false)
) {

    fun bind(loadState: LoadState) {

    }
}