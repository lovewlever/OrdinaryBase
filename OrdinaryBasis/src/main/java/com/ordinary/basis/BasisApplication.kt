package com.ordinary.basis

import android.app.Application

open class BasisApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.initialization(this)
    }
}