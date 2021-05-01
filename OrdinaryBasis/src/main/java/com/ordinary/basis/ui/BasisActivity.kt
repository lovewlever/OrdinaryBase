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


    fun <T : ViewDataBinding> BasisActivity<T>.setContentViewBinding(layoutId: () -> Int) {
        setContentView(
            DataBindingUtil.inflate<T>(LayoutInflater.from(this), layoutId(), null, false).also {
                this.binding = it
            }.root
        )

    }
}

