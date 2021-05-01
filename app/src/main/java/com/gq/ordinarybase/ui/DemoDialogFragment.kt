package com.gq.ordinarybase.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gq.ordinarybase.R
import com.gq.ordinarybase.databinding.DialogDemoBinding
import com.ordinary.basis.ui.BasisDialogFragment
import com.ordinary.basis.ui.DialogAttributes

class DemoDialogFragment: BasisDialogFragment<DialogDemoBinding>() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setDialogAttributes { d: DialogAttributes ->
            d
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setContentViewBinding(inflater, container, savedInstanceState) {

        R.layout.dialog_demo
    }



}