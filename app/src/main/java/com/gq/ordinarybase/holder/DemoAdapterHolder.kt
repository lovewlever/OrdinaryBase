package com.gq.ordinarybase.holder

import com.gq.ordinarybase.databinding.ItemDemoStringBinding
import com.ordinary.basis.ui.BasisRecyclerViewHolder

class DemoAdapterHolder(val binding: ItemDemoStringBinding) : BasisRecyclerViewHolder<String,ItemDemoStringBinding>(binding) {


    override fun onBindData(entity: String, position: Int, payloads: MutableList<Any>) {
        super.onBindData(entity, position, payloads)
        binding.atvText.text = entity
    }

    override fun onBindData(entity: String, position: Int) {
        super.onBindData(entity, position)
        binding.atvText.text = entity
    }

}