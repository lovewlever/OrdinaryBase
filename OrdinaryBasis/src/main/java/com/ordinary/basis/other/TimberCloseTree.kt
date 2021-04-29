package com.ordinary.basis.other

import timber.log.Timber

class TimberCloseTree: Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

    }
}