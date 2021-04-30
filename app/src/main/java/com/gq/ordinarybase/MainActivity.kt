package com.gq.ordinarybase

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ordinary.basis.common.MetadataCommon
import com.ordinary.basis.common.SnackbarCommon
import com.ordinary.basis.common.SystemUiController
import com.ordinary.basis.common.ToastCommon

class MainActivity : AppCompatActivity() {

    private val systemUiController by lazy { SystemUiController(window) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btClick).setOnClickListener {
            SnackbarCommon.makeSnackbar(findViewById(R.id.snackbarAnchor),"hello")
        }
        ToastCommon.showToast(MetadataCommon.getMetadataString("APP_CODE"))
    }

    override fun onResume() {
        super.onResume()
        systemUiController
            .setStatusBarIconColor(color = Color.RED, isLight = true)
            .setDecorFitsSystemWindows(false)
    }

}