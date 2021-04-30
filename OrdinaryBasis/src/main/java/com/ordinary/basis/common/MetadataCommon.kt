package com.ordinary.basis.common

import android.content.pm.PackageManager
import com.ordinary.basis.AppContext

/**
 * MetaData
 */
object MetadataCommon {

    fun getMetadataString(key: String): String =
        AppContext
            .application
            .packageManager
            .getApplicationInfo(
                AppContext
                    .application
                    .packageName, PackageManager
                    .GET_META_DATA
            )
            .metaData.getString(key) ?: ""

}