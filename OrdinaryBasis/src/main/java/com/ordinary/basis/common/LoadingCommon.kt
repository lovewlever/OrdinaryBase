package com.ordinary.basis.common

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.postDelayed
import com.ordinary.basis.R

/**
 * 加载提示
 * @Author: GQ
 * @Date: 2021/3/28 16:19
 */
object LoadingCommon {

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    fun showLoadingDialog(
        activity: Activity,
        msg: String = "加载中...",
        timeOut: Long = 5000
    ): LoadingDialog {
        val dialog = LoadingDialog(activity).apply {
            show()
            setText(msg)
        }
        handler.postDelayed({
            dialog.dismiss()
        }, timeOut)
        return dialog
    }

    fun showLoadingToastDialog(
        activity: Activity,
        msg: String,
        timeOut: Int = Toast.LENGTH_LONG
    ) {
        val dialog = LoadingDialog(activity).apply {
            show()
            setText(msg)
            hideProgressBar()
        }
        handler.postDelayed({
            dialog.dismiss()
        }, timeOut.toLong())
    }
}


class LoadingDialog(context: Context) :
    android.app.AlertDialog(context, R.style.TransparentDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.dialog_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(false)

    }

    fun setText(str: String) {
        findViewById<TextView>(R.id.tv_message).text = str
    }

    fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progressBar).gradientHideView(0)
    }
}