package com.ordinary.basis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ordinary.basis.extension.ifNotNullAndEmpty
import java.lang.Exception


class BasisRecyclerViewAdapter<E,VB: ViewDataBinding, VH: BasisRecyclerViewHolder<E,VB>>(val layoutId: Int):
    RecyclerView.Adapter<VH>() {

    private val datas by lazy { mutableListOf<E>() }

    fun setData(list: MutableList<E>) {
        val dataSize = itemCount
        notifyItemRangeRemoved(0, dataSize)
        this.datas.clear()
        this.datas.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    fun addData(e: E) {
        val dataSize = itemCount
        this.datas.add(e)
        notifyItemRangeInserted(dataSize, itemCount)
    }

    fun addDataAll(list: MutableList<E>) {
        val dataSize = itemCount
        this.datas.addAll(list)
        notifyItemRangeInserted(dataSize, itemCount)
    }

    fun clearData() {
        val dataSize = itemCount
        this.datas.clear()
        notifyItemRangeRemoved(0, dataSize)
    }

    fun removeData(pos: Int) {
        this.datas.filterIndexed { index, e -> pos == index }.ifNotNullAndEmpty { list: MutableList<E> ->
            this.datas.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    fun removeData(ee: E) {
        var pos = -1
        this.datas.filterIndexed { index, e ->
            if (ee == e) {
                pos = index
                true
            } else {
                false
            }
        }.ifNotNullAndEmpty { list: MutableList<E> ->
            this.datas.remove(ee)
            notifyItemRemoved(pos)
        }
    }

    override fun getItemCount(): Int = datas.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH  =
        BasisRecyclerViewHolder<E,VB>(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false) as VB) as VH


    override fun onBindViewHolder(
        holder: VH,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val e = this.datas[position]
        holder.onBindData(e, position, payloads)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = this.datas[position]
        holder.onBindData(e, position)
    }

}

open class BasisRecyclerViewHolder<E,VB: ViewDataBinding>(binding: VB) : RecyclerView.ViewHolder(binding.root) {

    open fun onBindData(entity: E, position: Int, payloads: MutableList<Any>) {

    }

    open fun onBindData(entity: E, position: Int) {

    }
}

fun <E,VB: ViewDataBinding,VH: BasisRecyclerViewHolder<E,VB>>
        newUnionAdapter(layoutId: Int):BasisRecyclerViewAdapter<E,VB, VH> {
    return BasisRecyclerViewAdapter(layoutId)
}

