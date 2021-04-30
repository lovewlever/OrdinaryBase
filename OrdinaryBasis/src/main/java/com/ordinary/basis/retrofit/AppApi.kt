package com.ordinary.basis.retrofit

import com.ordinary.basis.entities.ResultEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET("")
    suspend fun getAppConfig(@Query("name") name: String): ResultEntity<String>
}