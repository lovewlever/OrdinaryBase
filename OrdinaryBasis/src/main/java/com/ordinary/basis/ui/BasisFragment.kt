package com.ordinary.basis.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ordinary.basis.common.SystemUiController
import com.ordinary.basis.viewmodel.BasisViewModel

abstract class BasisFragment<T: ViewDataBinding>: Fragment() {

    protected val systemUiController by lazy {
        activity?.window?.let { SystemUiController(it) }
    }

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}

inline fun <reified T: ViewDataBinding> BasisFragment<T>.setContentViewBinding(inflater: LayoutInflater,
                                                                               container: ViewGroup?,
                                                                               savedInstanceState: Bundle?,
                                                                               layoutId: () -> Int): View {
    return DataBindingUtil.inflate<T>(inflater,layoutId(),container,false).apply {
        binding = this
    }.root
}