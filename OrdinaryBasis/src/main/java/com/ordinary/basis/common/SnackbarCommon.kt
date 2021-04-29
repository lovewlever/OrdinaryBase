package com.ordinary.basis.common

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.ordinary.basis.R

object SnackbarCommon {


    fun makeSnackbar(view: FrameLayout, msg: String) {
        val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
        val snackBarView = snackbar.view
        val layoutParams = snackBarView.layoutParams
        // 重新设置属性参数
        val cl = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,layoutParams.height)
        // 设置显示位置在上居中
        cl.setMargins(0,DensityUtil.dip2px(view.context,(DensityUtil.getScreenHeight(view.context) shr 3).toFloat()),0,0)
        cl.gravity = Gravity.CENTER_HORIZONTAL
        snackBarView.layoutParams = cl
        snackBarView.setBackgroundColor(Color.WHITE)
        (snackBarView as Snackbar.SnackbarLayout).addView(ImageView(view.context).apply {
            setImageResource(R.drawable.ic_baseline_check_24)
        })
        snackbar.show()
    }

}