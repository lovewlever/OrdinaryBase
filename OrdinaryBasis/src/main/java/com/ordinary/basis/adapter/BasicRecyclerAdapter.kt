package com.ordinary.basis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ordinary.basis.extension.ifNotNullAndEmpty

class BasicRecyclerAdapter<E>(var markArgs: Any = Any()) :
    RecyclerView.Adapter<BasicRecyclerHolder<E, *>>() {

    lateinit var onCreateViewHolderCall: (parent: ViewGroup, viewType: Int) -> BasicRecyclerHolder<E, *>
    val datas = mutableListOf<E>()
    var onItemClickListener: (View, E, Int) -> Unit = { _: View, _: E, _: Int -> }
    var onItemLongClickListener: (View, E, Int) -> Unit = { _: View, _: E, _: Int -> }

    fun setData(list: MutableList<E>) {
        val dataSize = itemCount
        this.datas.clear()
        notifyItemRangeRemoved(0, dataSize)
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
        this.datas.filterIndexed { index, e -> pos == index }
            .ifNotNullAndEmpty { list: MutableList<E> ->
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


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasicRecyclerHolder<E, *> {
        return onCreateViewHolderCall(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: BasicRecyclerHolder<E, *>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val e = this.datas[position]
        holder.onBindData(e, position, payloads)
    }

    override fun onBindViewHolder(holder: BasicRecyclerHolder<E, *>, position: Int) {
        val e = this.datas[position]
        holder.onBindData(e, position)
    }

}


open class BasicRecyclerHolder<E, VB : ViewDataBinding>(binding: VB, adapter: BasicRecyclerAdapter<E>) :
    RecyclerView.ViewHolder(binding.root) {

    protected val context = binding.root.context

    open fun onBindData(entity: E, position: Int, payloads: MutableList<Any>) {
        setOnClickListener(entity, position)
    }

    open fun onBindData(entity: E, position: Int) {
        setOnClickListener(entity, position)
    }

    protected open fun setOnClickListener(entity: E, position: Int) {

    }

}


inline fun <E, reified VH : BasicRecyclerHolder<E, *>>
        newOrdinaryAdapter(
    crossinline createViewHolder: () -> Int
): BasicRecyclerAdapter<E> {
    return BasicRecyclerAdapter<E>().apply {
        onCreateViewHolderCall = { parent: ViewGroup, viewType: Int ->
            val clazz = VH::class
            val vh: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                createViewHolder(),
                parent,
                false
            )
            val constructors = clazz.constructors
            constructors.toMutableList()[0].call(vh, this)
        }
    }
}

