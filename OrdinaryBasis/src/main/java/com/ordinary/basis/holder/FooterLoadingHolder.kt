package com.ordinary.basis.holder

import com.ordinary.basis.databinding.ItemFooterLoadingBinding
import com.ordinary.basis.ui.BasisRecyclerViewHolder
import timber.log.Timber

class FooterLoadingHolder(val binding: ItemFooterLoadingBinding) : BasisRecyclerViewHolder<String,ItemFooterLoadingBinding>(
    binding
) {

    override fun onBindDataByFooterView(entity: String?, position: Int, payloads: MutableList<Any>) {
        super.onBindDataByFooterView(entity, position, payloads)
        Timber.d("添加Footer${position}")
        binding.atvTextView.text = "$entity"
    }
}