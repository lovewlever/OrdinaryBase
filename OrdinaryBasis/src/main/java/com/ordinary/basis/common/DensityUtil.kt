package com.ordinary.basis.common

import android.content.Context
import com.ordinary.basis.AppContext

/**
 * px/dp/sp相互转换工具
 * @author 01218
 * @created 2019/2/21
 */
object DensityUtil {
    var RATIO = 0.95f//缩放比例值

    /**
     * px 转 dp【按照一定的比例】
     */
    @JvmStatic
    fun px2dipByRatio( pxValue: Float, ratio: Float): Int {
        val scale = getScreenDendity() * ratio
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px【按照一定的比例】
     */
    @JvmStatic
    fun dip2pxByRatio(dpValue: Float, ratio: Float): Int {
        val scale = getScreenDendity() * ratio
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px 转 dp
     */
    @JvmStatic
    fun px2dip(pxValue: Float): Int {
        val scale = getScreenDendity()
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px
     */
    @JvmStatic
    fun dip2px(dpValue: Float): Int {
        val scale = getScreenDendity()
        return (dpValue * scale + 0.5f).toInt()
    }

    /**获取屏幕的宽度（像素） */
    @JvmStatic
    fun getScreenWidth(): Int {
        return AppContext.application.resources.displayMetrics.widthPixels
    }

    /**获取屏幕的宽度（dp） */
    @JvmStatic
    fun getScreenWidthDp(): Int {
        val scale = getScreenDendity()
        return (AppContext.application.resources.displayMetrics.widthPixels / scale).toInt()
    }

    /**获取屏幕的高度（像素） */
    @JvmStatic
    fun getScreenHeight(): Int {
        return AppContext.application.resources.displayMetrics.heightPixels
    }

    /**获取屏幕的高度（像素） */
    @JvmStatic
    fun getScreenHeightDp(): Int {
        val scale = getScreenDendity()
        return (AppContext.application.resources.displayMetrics.heightPixels / scale).toInt()
    }

    /**屏幕密度比例 */
    @JvmStatic
    fun getScreenDendity(): Float {
        return AppContext.application.resources.displayMetrics.density
    }

    /**
     * 获取状态栏的高度 72px
     */
    @JvmStatic
    fun getStatusBarHeight(): Int {
        var statusHeight = -1
        try {
            val aClass = Class.forName("com.android.internal.R\$dimen")
            val `object` = aClass.newInstance()
            val height = Integer.parseInt(aClass.getField("status_bar_height").get(`object`).toString())
            statusHeight = AppContext.application.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight

    }

    /**
     * 指定机型（displayMetrics.xdpi）下dp转px
     */
    @JvmStatic
    fun dpToPxByDevice(dp: Int): Int {
        return Math.round(dp.toFloat() * getPixelScaleFactor())
    }

    /**
     * 指定机型（displayMetrics.xdpi）下px 转 dp
     */
    @JvmStatic
    fun pxToDpByDevice(px: Int): Int {
        return Math.round(px.toFloat() / getPixelScaleFactor())
    }

    /**获取水平方向的dpi的密度比例值
     * 2.7653186 */
    @JvmStatic
    fun getPixelScaleFactor(): Float {
        val displayMetrics = AppContext.application.resources.displayMetrics
        return displayMetrics.xdpi / 160.0f
    }
}
