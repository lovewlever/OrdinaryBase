package com.gq.ordinarybase

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gq.ordinarybase.databinding.ItemDemoStringBinding
import com.gq.ordinarybase.holder.DemoAdapterHolder
import com.ordinary.basis.common.MetadataCommon
import com.ordinary.basis.common.SystemUiController
import com.ordinary.basis.common.ToastCommon
import com.ordinary.basis.holder.FooterLoadingHolder
import com.ordinary.basis.ui.newUnionAdapter

class MainActivity : AppCompatActivity() {

    private val systemUiController by lazy { SystemUiController(window) }
    private val adapter by lazy {
        newUnionAdapter<String, FooterLoadingHolder,DemoAdapterHolder> {
            R.layout.item_demo_string
        }.apply {
            addDataAll(mutableListOf("1", "2", "1", "2"))
        }
    }
    private var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btClick).setOnClickListener {
            i ++
            adapter.addDataAll(mutableListOf("1"))
            if (i > 3) {
                adapter.addFooterView("没有更多数据")

            } else {
                adapter.addFooterView("${System.currentTimeMillis()}")
            }

        }

        findViewById<RecyclerView>(R.id.recyclerView).also { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = this.adapter
        }
    }

    override fun onResume() {
        super.onResume()
        systemUiController
            .setStatusBarIconColor(color = Color.RED, isLight = true)
            .setDecorFitsSystemWindows(false)
    }

}