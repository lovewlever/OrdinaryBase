package com.ordinary.basis.extension

import android.net.Uri
import com.facebook.common.util.UriUtil
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView
import com.ordinary.basis.AppContext


inline fun SimpleDraweeView.loadResourceImage(resId: Int) {
    this.setActualImageResource(resId)
    this.hierarchy = GenericDraweeHierarchyBuilder(AppContext.application.resources).apply {
        this.actualImageScaleType = ScalingUtils.ScaleType.CENTER_CROP
    }.build()
}


inline fun SimpleDraweeView.loadLocalPathImage(path: String) {
    this.setImageURI(
        Uri.Builder()
            .scheme(UriUtil.LOCAL_FILE_SCHEME)
            .path(path)
            .build(),
        AppContext.application
    )
    this.hierarchy = GenericDraweeHierarchyBuilder(AppContext.application.resources).apply {
        this.actualImageScaleType = ScalingUtils.ScaleType.CENTER_CROP
    }.build()
}


inline fun SimpleDraweeView.loadUriImage(uri: Uri) {
    this.setImageURI(
        uri,
        AppContext.application
    )
    this.hierarchy = GenericDraweeHierarchyBuilder(AppContext.application.resources).apply {
        this.actualImageScaleType = ScalingUtils.ScaleType.CENTER_CROP
    }.build()
}