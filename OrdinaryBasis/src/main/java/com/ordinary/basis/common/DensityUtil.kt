package com.ordinary.basis.common

import android.content.Context

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
    fun px2dipByRatio(context: Context, pxValue: Float, ratio: Float): Int {
        val scale = getScreenDendity(context) * ratio
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px【按照一定的比例】
     */
    @JvmStatic
    fun dip2pxByRatio(context: Context, dpValue: Float, ratio: Float): Int {
        val scale = getScreenDendity(context) * ratio
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px 转 dp
     */
    @JvmStatic
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = getScreenDendity(context)
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px
     */
    @JvmStatic
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = getScreenDendity(context)
        return (dpValue * scale + 0.5f).toInt()
    }

    /**获取屏幕的宽度（像素） */
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**获取屏幕的宽度（dp） */
    @JvmStatic
    fun getScreenWidthDp(context: Context): Int {
        val scale = getScreenDendity(context)
        return (context.resources.displayMetrics.widthPixels / scale).toInt()
    }

    /**获取屏幕的高度（像素） */
    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**获取屏幕的高度（像素） */
    @JvmStatic
    fun getScreenHeightDp(context: Context): Int {
        val scale = getScreenDendity(context)
        return (context.resources.displayMetrics.heightPixels / scale).toInt()
    }

    /**屏幕密度比例 */
    @JvmStatic
    fun getScreenDendity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    /**
     * 获取状态栏的高度 72px
     */
    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        var statusHeight = -1
        try {
            val aClass = Class.forName("com.android.internal.R\$dimen")
            val `object` = aClass.newInstance()
            val height = Integer.parseInt(aClass.getField("status_bar_height").get(`object`).toString())
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight

    }

    /**
     * 指定机型（displayMetrics.xdpi）下dp转px
     */
    @JvmStatic
    fun dpToPxByDevice(context: Context, dp: Int): Int {
        return Math.round(dp.toFloat() * getPixelScaleFactor(context))
    }

    /**
     * 指定机型（displayMetrics.xdpi）下px 转 dp
     */
    @JvmStatic
    fun pxToDpByDevice(context: Context, px: Int): Int {
        return Math.round(px.toFloat() / getPixelScaleFactor(context))
    }

    /**获取水平方向的dpi的密度比例值
     * 2.7653186 */
    @JvmStatic
    fun getPixelScaleFactor(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.xdpi / 160.0f
    }
}
