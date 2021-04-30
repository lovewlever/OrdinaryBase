package com.ordinary.basis.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.ViewDataBindingKtx
import androidx.fragment.app.Fragment
import com.ordinary.basis.common.SystemUiController

abstract class BasisFragment<T: ViewDataBinding>: Fragment() {

    protected val systemUiController by lazy {
        activity?.window?.let { SystemUiController(it) }
    }
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        onCreateViewBinding(inflater, container, savedInstanceState).apply {
            binding = this
            root.isClickable = true
            root.isFocusable = true
        }.root


    abstract fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T


}