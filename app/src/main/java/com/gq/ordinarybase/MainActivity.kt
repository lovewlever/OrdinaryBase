package com.gq.ordinarybase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ordinary.basis.common.SnackbarCommon

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btClick).setOnClickListener {
            SnackbarCommon.makeSnackbar(findViewById(R.id.snackbarAnchor),"hello")
        }
    }

}