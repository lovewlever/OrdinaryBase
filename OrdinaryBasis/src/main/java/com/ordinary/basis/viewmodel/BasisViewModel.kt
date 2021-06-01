package com.ordinary.basis.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.ordinary.basis.entities.ResultEntity


open class BasisViewModel(application: Application): AndroidViewModel(application), LifecycleObserver {

    protected val _resultErrorEntity = MutableLiveData<ResultEntity<*>>()
    val resultErrorEntity: LiveData<ResultEntity<*>> = _resultErrorEntity

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun lifecycleEventOnStop() {
        _resultErrorEntity.value = ResultEntity<String>()
    }
}