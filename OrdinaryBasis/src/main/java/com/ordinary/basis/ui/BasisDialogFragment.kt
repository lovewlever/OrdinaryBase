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

    abstract fun onCreateViewNew(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogAttributes?.isHiddenTitle?.takeIf { it }?.let {
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return onCreateViewNew(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialogAttributes?.let { attr ->
            isCancelable = attr.isCancelableClose
            dialog?.let { dia ->
                dia.setCancelable(attr.isCancelableClose)
                dia.window?.let { wind ->
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
                        attr.windowAnimations.takeIf { it != 0 }?.let {
                            attributes.windowAnimations = it
                        }
                    }
            }
        }
        super.onResume()
    }


    fun setDialogAttributes(block: (DialogAttributes) -> DialogAttributes) {
        this.dialogAttributes = block(DialogAttributes())
    }

    fun setContentViewBinding(
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
    var isHiddenTitle = true //????????????
    var isBgTransparent = false //????????????
    var isFullScreen = false //????????????
    var isCancelableClose = true //??????????????????
    var gravity = Gravity.CENTER
    var windowAnimations = 0
    var width = ViewGroup.LayoutParams.WRAP_CONTENT
    var height = ViewGroup.LayoutParams.WRAP_CONTENT
}


