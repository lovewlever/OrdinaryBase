package com.ordinary.basis.common

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.ordinary.basis.AppContext

/**
 * @Description: Toast
 * @Author: GQ
 * @Date: 2021/4/2 14:05
 * 不推荐使用
 * 自定义吐司视图已弃用。
 * 应用程序可以使用makeText（Context，CharSequence，int）方法创建标准的文本吐司，或者在前台使用Snackbar。
 * 从Android Build.VERSION_CODES.R开始，在后台定位到API级别Build.VERSION_CODES.R或更高版本的应用将不会显示自定义的Toast视图。
 */
@Deprecated("")
object ToastCommon {

    private val LAYOUT_PADDING by lazy { DensityUtil.dip2px(AppContext.application, 8F) }
    private val LAYOUT_WIDTH_MAX by lazy { DensityUtil.dip2px(AppContext.application, 160F) }
    private val LAYOUT_RADIUS by lazy { DensityUtil.dip2px(AppContext.application, 6F) }

    fun showToast(str: String?, duration: Int = Toast.LENGTH_LONG) {
        Toast(AppContext.application).also { t: Toast ->
            t.duration = duration
            t.view = getToastView(str)
            t.setGravity(Gravity.CENTER, 0, 0)
        }.show()
    }


    private fun getToastView(text: String?): View =
        getLayout().apply {
            addView(AppCompatTextView(AppContext.application).also { atv: AppCompatTextView ->
                atv.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                atv.maxWidth = LAYOUT_WIDTH_MAX
                atv.setTextColor(Color.WHITE)
                atv.text = "$text"
            })
        }


    private fun getLayout(): LinearLayout =
        LinearLayout(AppContext.application).also { l: LinearLayout ->
            l.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            l.background = getShape()
            l.gravity = Gravity.CENTER
            l.orientation = LinearLayout.VERTICAL
            l.setPadding(LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING)
        }


    private fun getShape(): GradientDrawable =
        GradientDrawable().also { gd: GradientDrawable ->
            gd.setColor(Color.parseColor("#6F000000"))
            gd.cornerRadius = LAYOUT_RADIUS.toFloat()
        }

}