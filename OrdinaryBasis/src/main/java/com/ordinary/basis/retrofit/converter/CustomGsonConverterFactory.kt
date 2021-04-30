package com.ordinary.basis.retrofit.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @Description:
 * @Author: GQ
 * @Date: 2021/4/5 15:46
 */
class CustomGsonConverterFactory(val gson: Gson): Converter.Factory() {

    companion object {
        fun create(): CustomGsonConverterFactory {
            return create(Gson())
        }
        fun create(gson: Gson?): CustomGsonConverterFactory {
            if (gson == null) throw NullPointerException("gson == null")
            return CustomGsonConverterFactory(gson)
        }
    }


    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return CustomResponseBodyConverter(gson,adapter)
    }
}