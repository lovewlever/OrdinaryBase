package com.ordinary.basis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import com.ordinary.basis.repository.AppRepository


open class BasisViewModel(application: Application): AndroidViewModel(application), LifecycleObserver {

    suspend fun geConfig() {
        val appConfig = AppRepository().getAppConfig()
    }

}