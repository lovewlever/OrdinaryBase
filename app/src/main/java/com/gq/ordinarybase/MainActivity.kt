package com.gq.ordinarybase

import android.graphics.Color
import android.os.Bundle
import com.gq.ordinarybase.databinding.ActivityMainBinding
import com.ordinary.basis.ui.BasisActivity
import com.ordinary.basis.ui.setContentViewBinding

class MainActivity : BasisActivity<ActivityMainBinding>() {


    private var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewBinding { R.layout.activity_main }
        supportFragmentManager.beginTransaction().add(R.id.layout, MainFragment(),"0").commit()
    }

    override fun onResume() {
        super.onResume()
        systemUiController
            .setStatusBarIconColor(color = Color.RED, isLight = true)
            .setDecorFitsSystemWindows(false)
    }

}