package com.ordinary.basis.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

/**
 * View
 * @Author: GQ
 * @Date: 2021/3/31 17:56
 */
object ViewCommon {

    const val DURATION = 500L

    /**
     * 隐藏View
     */
    fun gradientHideView(vararg view: View?, duration: Long = DURATION) {
        view.forEach { v: View? ->
            if (v?.visibility == View.GONE) return@forEach
            if (duration == 0L) {
                v?.visibility = View.GONE
            } else {
                v?.apply {
                    animate().alpha(0F).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            v.visibility = View.GONE
                        }
                    }).start()
                }
            }
        }
    }

    /**
     * 让View不可见
     */
    fun gradientInvisibleView(vararg view: View?, duration: Long = DURATION) {
        view.forEach { v: View? ->
            if (v?.visibility == View.INVISIBLE) return@forEach
            if (duration == 0L) {
                v?.visibility = View.INVISIBLE
            } else {
                v?.apply {
                    animate().alpha(0F).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            v.visibility = View.INVISIBLE
                        }
                    }).start()
                }
            }
        }
    }


    /**
     * 让View可见
     */
    fun gradientVisibleView(vararg view: View?, duration: Long = DURATION) {
        view.forEach { v: View? ->
            if (v?.visibility == View.VISIBLE) return@forEach
            if (duration == 0L) {
                v?.visibility = View.VISIBLE
            } else {
                v?.apply {
                    alpha = 0F
                    visibility = View.VISIBLE
                    animate().alpha(1F).setDuration(duration).setListener(null).start()
                }
            }
        }
    }
}


fun View.gradientVisibleView(duration: Long = ViewCommon.DURATION) {
    ViewCommon.gradientVisibleView(this, duration = duration)
}


fun View.gradientInvisibleView(duration: Long = ViewCommon.DURATION) {
    ViewCommon.gradientInvisibleView(this, duration = duration)
}

fun View.gradientHideView(duration: Long = ViewCommon.DURATION) {
    ViewCommon.gradientHideView(this, duration = duration)
}


