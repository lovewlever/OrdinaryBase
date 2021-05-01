package com.gq.ordinarybase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gq.ordinarybase.R
import com.gq.ordinarybase.databinding.FragmentMainBinding
import com.ordinary.basis.ui.BasisFragment

class MainFragment: BasisFragment<FragmentMainBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setContentViewBinding(inflater,container,savedInstanceState) {
        R.layout.fragment_main
    }

}