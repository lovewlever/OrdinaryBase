package com.gq.ordinarybase.ui

import android.graphics.Color
import android.os.Bundle
import com.gq.ordinarybase.R
import com.gq.ordinarybase.databinding.ActivityMainBinding
import com.ordinary.basis.entities.ResultSealed
import com.ordinary.basis.ui.BasisActivity

class MainActivity : BasisActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewBinding { R.layout.activity_main }
        supportFragmentManager.beginTransaction().add(R.id.layout, MainFragment(),"0").commit()
    }

    override fun onResume() {
        super.onResume()
        systemUiController
            .setStatusBarColor(color = Color.RED, isLight = true)
            .setDecorFitsSystemWindows(false)
    }

}