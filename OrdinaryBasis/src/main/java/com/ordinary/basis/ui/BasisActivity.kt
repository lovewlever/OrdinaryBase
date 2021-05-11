package com.ordinary.basis.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ordinary.basis.R
import com.ordinary.basis.common.SystemUiController

abstract class BasisActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected val systemUiController by lazy {
        SystemUiController(window)
    }

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun setContentViewBinding(layoutId: () -> Int) {
        binding = DataBindingUtil.setContentView(this, layoutId())
    }
}

