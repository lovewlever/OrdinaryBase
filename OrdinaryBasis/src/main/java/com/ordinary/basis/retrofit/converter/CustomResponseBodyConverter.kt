package com.ordinary.basis.retrofit.converter

import com.google.gson.*
import com.ordinary.basis.entities.ResultEntity
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.StringReader

/**
 * @Description:
 * @Author: GQ
 * @Date: 2021/4/5 15:50
 */
class CustomResponseBodyConverter<T>(val gson: Gson, val adapter: TypeAdapter<T>): Converter<ResponseBody,T> {


    override fun convert(value: ResponseBody): T? {
        val string = value.string()
        var parseString = JsonParser.parseString(string)
        if (parseString is JsonObject) {
            val data = parseString.get("data")
            // 如果 data是对象 转为数组
            if (data is JsonObject) {
                parseString.remove("data")
                parseString.add("data",JsonArray().apply { add(data) })
            }
        } else {
            parseString = JsonParser.parseString(gson.toJson(ResultEntity<T>()))
        }

        val toJson = gson.toJson(parseString)

        return adapter.read(gson.newJsonReader(StringReader(toJson)))
    }
}