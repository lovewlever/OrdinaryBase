package com.ordinary.basis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ordinary.basis.entities.ResultEntity


open class BasisViewModel(application: Application): AndroidViewModel(application), LifecycleObserver {

    protected val _resultErrorEntity = MutableLiveData<ResultEntity<*>>()
    val resultErrorEntity: LiveData<ResultEntity<*>> = _resultErrorEntity

}