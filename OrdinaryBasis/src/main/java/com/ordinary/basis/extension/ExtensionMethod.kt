package com.ordinary.basis.extension

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.ordinary.basis.common.RegexConstant


/**
 * 大写字母转下划线加小写字母
 */
fun String.camelToUnderline(): String {
    if (this == "") {
        return "".toLowerCase()
    }
    val stringBuilder = StringBuilder(this)
    val mc = RegexConstant.REGEX_CAMEL.matcher(this)

    var i = 0
    while (mc.find()) {
        stringBuilder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
        i++
    }

    if ('_' == stringBuilder.elementAt(0)) {
        stringBuilder.deleteCharAt(0)
    }
    return stringBuilder.toString()
}

/**
 * 扩展函数，只监听onTabSelected
 */
inline fun TabLayout.addOnTabSelectedListener(crossinline onTabSelected:(tab: TabLayout.Tab?) -> Unit = {}) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
        override fun onTabReselected(tab: TabLayout.Tab?) {}
        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabSelected(tab: TabLayout.Tab?) {
            onTabSelected(tab)
        }
    })
}

/**
 * List<String>转字符串
 */
inline fun Collection<String>.toStringSplicing(delimiter: String): String {
    val sb = StringBuilder()
    this.forEach { s: String ->
        sb.append(s).append(delimiter)
    }
    if (sb.isNotEmpty()) {
        sb.deleteCharAt(sb.length - 1)
    }
    return sb.toString()
}

/**
 * 字符串转MutableList<String>
 */
inline fun String.toStringSegmentation(delimiter: String): MutableList<String> {
    return this.split(delimiter).ifNotNullAndEmpty {
        it.remove("")
        it
    } ?: mutableListOf()
}

inline fun <E,R> Collection<E>?.ifNotNullAndEmpty(block: (MutableList<E>) -> R): R? {
    if (!this.isNullOrEmpty()) {
        return block(this.toMutableList())
    }
    return null
}


inline fun <I, O>  Context.registerForActivityResult(contract :ActivityResultContract<I, O>,
                                                     crossinline callback :(O?) -> Unit): ActivityResultLauncher<I>? {
    return when (this) {
        is Fragment -> {
            registerForActivityResult(contract) {
                callback(it)
            }
        }
        is AppCompatActivity -> {
            registerForActivityResult(contract) {
                callback(it)
            }
        }
        else -> {
            null
        }
    }
}


