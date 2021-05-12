package com.ordinary.basis.common

import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ordinary.basis.AppContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object GlideCommon {

    suspend fun getRealPathForLocalUri(vararg uri: Uri, mutableList: (MutableList<File>) -> Unit) {
        withContext(Dispatchers.Default) {
            val list = mutableListOf<File>()
            uri.forEach { i: Uri ->
                Glide.with(AppContext.application).asFile().load(i).into(object :CustomTarget<File>() {
                    override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                        list.add(resource)
                        if (list.size == uri.size) {
                            mutableList(list)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
            }
        }
    }
}