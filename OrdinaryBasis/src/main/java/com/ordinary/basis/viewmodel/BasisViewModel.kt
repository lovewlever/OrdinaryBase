package com.ordinary.basis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ordinary.basis.entities.ResultEntity
import com.ordinary.basis.repository.AppRepository


open class BasisViewModel(application: Application): AndroidViewModel(application), LifecycleObserver {

    private val _resultErrorEntity = MutableLiveData<ResultEntity<*>>()
    val resultErrorEntity: LiveData<ResultEntity<*>> = _resultErrorEntity

}