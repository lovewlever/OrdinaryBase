package com.ordinary.basis.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.ordinary.basis.common.SystemUiController

abstract class BasisActivity<T: ViewDataBinding>: AppCompatActivity() {

    protected val systemUiController by lazy {
        SystemUiController(window)
    }

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setViewBinding(LayoutInflater.from(this)).apply {
            binding = this
        }.root)
    }

    abstract fun setViewBinding(inflater: LayoutInflater): T
}