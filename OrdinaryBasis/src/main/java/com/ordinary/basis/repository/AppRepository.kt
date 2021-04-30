package com.ordinary.basis.repository

import com.ordinary.basis.entities.ResultEntity
import com.ordinary.basis.retrofit.AppApi
import com.ordinary.basis.retrofit.RetrofitCommon

class AppRepository {

    suspend fun getAppConfig() :ResultEntity<String> {
        return try {
            RetrofitCommon.retrofit(AppApi::class).getAppConfig("config")
        } catch (e: Exception) {
            ResultEntity()
        }
    }

}