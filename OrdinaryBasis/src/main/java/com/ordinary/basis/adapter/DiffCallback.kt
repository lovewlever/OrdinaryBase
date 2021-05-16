package com.ordinary.basis.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffCallback<E>: DiffUtil.ItemCallback<E>() {
    override fun areItemsTheSame(oldItem: E, newItem: E): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: E, newItem: E): Boolean {
        return oldItem == newItem
    }
}