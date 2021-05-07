package com.ordinary.basis.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ordinary.basis.R
import com.ordinary.basis.extension.ifNotNullAndEmpty
import java.lang.Exception
import kotlin.reflect.full.declaredMemberFunctions


class BasisRecyclerViewAdapter<E>(var markArgs: Any = Any()) :
    RecyclerView.Adapter<BasisRecyclerViewHolder<E, *>>() {

    companion object {
        const val VIEW_TYPE_FOOTER = 0x21
        const val VIEW_TYPE_NOMNAL = 0x23
    }

    lateinit var onCreateViewHolderCall: (parent: ViewGroup, viewType: Int) -> BasisRecyclerViewHolder<E, *>
    private val datas by lazy { mutableListOf<E>() }
    private var recyclerView: RecyclerView? = null
    private var isAddFooterView: Boolean = false
    private var footerViewData: E? = null


    fun addFooterView(footerViewData: E) {
        this.footerViewData = footerViewData
        this.isAddFooterView = true
        ifGridLayoutManager()
        notifyItemInserted(itemCount)
    }

    private fun ifGridLayoutManager() {
        this.recyclerView?.let {
            val layoutManager: RecyclerView.LayoutManager? = this.recyclerView?.layoutManager
            if (layoutManager is GridLayoutManager) {
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (getItemViewType(position) == VIEW_TYPE_FOOTER) layoutManager.spanCount else 1
                    }
                }
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

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

    override fun getItemCount(): Int = if (isAddFooterView) datas.size + 1 else datas.size


    override fun getItemViewType(position: Int): Int {
        return if (isAddFooterView && datas.size == position) VIEW_TYPE_FOOTER else VIEW_TYPE_NOMNAL
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasisRecyclerViewHolder<E, *> {
        return onCreateViewHolderCall(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: BasisRecyclerViewHolder<E, *>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (getItemViewType(position) == VIEW_TYPE_FOOTER) {
            holder.onBindDataByFooterView(footerViewData, position, payloads)
        } else {
            val e = this.datas[position]
            holder.onBindData(e, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: BasisRecyclerViewHolder<E, *>, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_FOOTER) {

        } else {
            val e = this.datas[position]
            holder.onBindData(e, position)
        }

    }

}

open class BasisRecyclerViewHolder<E, VB : ViewDataBinding>(binding: VB, adapter: BasisRecyclerViewAdapter<E>) :
    RecyclerView.ViewHolder(binding.root) {

    protected val context = binding.root.context

    open fun onBindData(entity: E, position: Int, payloads: MutableList<Any>) {
        setOnClickListener(entity, position)
    }

    open fun onBindData(entity: E, position: Int) {
        setOnClickListener(entity, position)
    }

    open fun onBindDataByFooterView(entity: E?, position: Int, payloads: MutableList<Any>) {
        entity?.let {
            setOnClickListener(it, position)
        }
    }

    protected open fun setOnClickListener(entity: E, position: Int) {

    }

}

inline fun <E, reified FVH : BasisRecyclerViewHolder<E, *>,
        reified VH : BasisRecyclerViewHolder<E, *>>
        newUnionAdapter(
    crossinline createViewHolderFooter: () -> Int,
    crossinline createViewHolder: () -> Int
): BasisRecyclerViewAdapter<E> {
    return BasisRecyclerViewAdapter<E>().apply {
        onCreateViewHolderCall = { parent: ViewGroup, viewType: Int ->
            when (viewType) {
                BasisRecyclerViewAdapter.VIEW_TYPE_FOOTER -> {
                    val clazz = FVH::class
                    val vh: ViewDataBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        createViewHolderFooter(),
                        parent,
                        false
                    )
                    val constructors = clazz.constructors
                    constructors.toMutableList()[0].call(vh, this)
                }
                else -> {
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
    }
}

