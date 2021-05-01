package com.ordinary.basis.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment


abstract class BasisDialogFragment<T : ViewDataBinding> : DialogFragment() {

    protected lateinit var binding: T
    private var dialogAttributes: DialogAttributes? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialogAttributes?.let { attr ->
            isCancelable = attr.isCancelableClose
            dialog?.let { dia ->
                dia.setCancelable(attr.isCancelableClose)
                dia.window?.let { wind ->
                    attr.isHiddenTitle.takeIf { it }
                        ?.let { wind.requestFeature(Window.FEATURE_NO_TITLE) }
                    attr.isBgTransparent.takeIf { it }?.let {
                        wind.setBackgroundDrawable(
                            ColorDrawable(
                                Color.TRANSPARENT
                            )
                        )
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        dialogAttributes?.let { attr ->
            dialog?.window?.let { wind ->
                wind.attributes =
                    wind.attributes?.also { attributes ->
                        attr.isFullScreen.takeIf { it }?.let {
                            attributes.height = ViewGroup.LayoutParams.MATCH_PARENT
                            attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
                        } ?: let {
                            attributes.height = attr.height
                            attributes.width = attr.width
                        }
                        attributes.gravity = attr.gravity
                        attributes.windowAnimations = attr.windowAnimations
                    }
            }
        }
        super.onResume()
    }


    fun <T : ViewDataBinding> BasisDialogFragment<T>.setDialogAttributes(block: (DialogAttributes) -> DialogAttributes) {
        this.dialogAttributes = block(DialogAttributes())
    }

    fun <T : ViewDataBinding> BasisDialogFragment<T>.setContentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        layoutId: () -> Int
    ): View {
        return DataBindingUtil.inflate<T>(inflater, layoutId(), container, false).also {
            this.binding = it
        }.root
    }
}


class DialogAttributes {
    var isHiddenTitle = true //隐藏标题
    var isBgTransparent = false //背景透明
    var isFullScreen = false //全屏显示
    var isCancelableClose = true //点击外部关闭
    var gravity = Gravity.CENTER
    var windowAnimations = 0
    var width = ViewGroup.LayoutParams.WRAP_CONTENT
    var height = ViewGroup.LayoutParams.WRAP_CONTENT
}


