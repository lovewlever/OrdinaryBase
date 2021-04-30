package com.ordinary.basis.common

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import com.ordinary.basis.AppContext


/**
 * @see
 * @author 01218
 * @version 1.0
 * @date 20-2-26
 */


interface SystemUiController {
    fun findStatusBarHeight(): Int

    fun setDecorFitsSystemWindows(
        decorFitsSystemWindows: Boolean = false
    ): SystemUiController

    fun setStatusBarIconColor(
        color: Int = Color.TRANSPARENT,
        isLight: Boolean = false
    ): SystemUiController

}


fun SystemUiController(window: Window): SystemUiController =
    SystemUiControllerImpl(window)

private class SystemUiControllerImpl constructor(val window: Window) : SystemUiController {

    override fun findStatusBarHeight(): Int {
        val resourceId =
            AppContext.application.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return AppContext.application.resources.getDimensionPixelSize(resourceId)
        }
        return DensityUtil.dip2px(AppContext.application, 26F)
    }

    override fun setDecorFitsSystemWindows(
        decorFitsSystemWindows: Boolean
    ): SystemUiController {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(decorFitsSystemWindows)
            window
                .decorView
                .windowInsetsController?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
        } else {
            window
                .decorView
                .systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        return this
    }

    override fun setStatusBarIconColor(
        color: Int,
        isLight: Boolean
    ): SystemUiController {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        //activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (isLight) {
            setStatusBarIconLightColor()
        } else {
            setStatusBarIconDarkColor()
        }
        return this
    }

    /**
     * 状态栏深色Icon
     */
    private fun setStatusBarIconDarkColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window
                .decorView
                .windowInsetsController
                ?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
        } else {
            window
                .decorView
                .systemUiVisibility = window
                .decorView
                .systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * 状态栏亮色Icon
     */
    private fun setStatusBarIconLightColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window
                .decorView
                .windowInsetsController
                ?.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
        } else {
            window
                .decorView
                .systemUiVisibility = window
                .decorView
                .systemUiVisibility
        }
    }

}