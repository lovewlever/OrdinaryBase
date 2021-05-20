package com.ordinary.basis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 *
 */
class BasicPagingRecyclerAdapter<E : Any>(
    var markArgs: Any = Any(),
    diffCallback: DiffUtil.ItemCallback<E>
) : PagingDataAdapter<E, BasicPagingRecyclerHolder<E, *>>(diffCallback) {

    lateinit var onCreateViewHolderCall: (parent: ViewGroup, viewType: Int) -> BasicPagingRecyclerHolder<E, *>
    var onItemClickListener: (View, E, Int) -> Unit = { _: View, _: E, _: Int -> }
    var onItemLongClickListener: (View, E, Int) -> Unit = { _: View, _: E, _: Int -> }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasicPagingRecyclerHolder<E, *> {
        return onCreateViewHolderCall(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: BasicPagingRecyclerHolder<E, *>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        getItem(position)?.let { e ->
            holder.onBindData(e, position, payloads)
        }

    }

    override fun onBindViewHolder(holder: BasicPagingRecyclerHolder<E, *>, position: Int) {
        getItem(position)?.let { e ->
            holder.onBindData(e, position)
        }
    }

}

open class BasicPagingRecyclerHolder<E : Any, VB : ViewDataBinding>(
    binding: VB,
    adapter: BasicPagingRecyclerAdapter<E>
) : RecyclerView.ViewHolder(binding.root) {

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

inline fun <E : Any, reified VH : BasicPagingRecyclerHolder<E, *>> newPagingRecyclerAdapter(
    loadStateHeaderAndFooterRecyclerView: RecyclerView? = null,
    crossinline createViewHolder: () -> Int
): BasicPagingRecyclerAdapter<E> {
    return BasicPagingRecyclerAdapter<E>(diffCallback = DiffCallback()).apply {
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
    }.apply {
        loadStateHeaderAndFooterRecyclerView?.adapter = withLoadStateHeaderAndFooter(
            header = LoadStateHeaderRecyclerAdapter(this::refresh),
            footer = LoadStateFooterRecyclerAdapter(this::retry)
        )
    }
}

