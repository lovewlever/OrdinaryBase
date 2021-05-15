package com.ordinary.basis.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ordinary.basis.common.SystemUiController

abstract class BasisFragment<T : ViewDataBinding> : Fragment() {

    protected val systemUiController by lazy {
        activity?.window?.let { SystemUiController(it) }
    }

    protected lateinit var binding: T
    // 是否已执行过onViewCreatedSingle
    private var itHasBeenExecuted = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!itHasBeenExecuted) {
            onViewCreatedSingle(view, savedInstanceState)
            itHasBeenExecuted = true
        }
    }

    protected open fun onViewCreatedSingle(view: View, savedInstanceState: Bundle?) {

    }

    fun setContentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        layoutId: () -> Int
    ): View {
        return if (this::binding.isInitialized) {
            binding.root
        } else {
            DataBindingUtil.inflate<T>(inflater, layoutId(), container, false).also {
                this.binding = it
            }.root
        }
    }

}

