package com.ordinary.basis.retrofit

import com.ordinary.basis.retrofit.converter.CustomGsonConverterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.net.Proxy
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * @Description:
 * @Author: GQ
 * @Date: 2021/4/2 16:42
 */
object RetrofitCommon {

    private val cookieStore = HashMap<String, List<Cookie>>()
    private lateinit var retrofitInstance: Retrofit

    fun <T : Any> retrofit(service: KClass<T>): T {
        return retrofitInstance.create(service.java)
    }

    fun initialization(baseUrl: String) {
        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .cookieJar(saveCookie())
                .addInterceptor(printResponse())
                .retryOnConnectionFailure(true)//允许重试
                //.addInterceptor(loginEventInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build()
        /*okHttpClient = if ("" == ApiConstants.CACHE_PATH) {
            builder.build()
        } else {
            builder.cache(Cache(File(ApiConstants.CACHE_PATH), (10 * 1024 * 1024).toLong())).build()
        } */
        retrofitInstance = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(CustomGsonConverterFactory.create())
            .build()
    }


    /**
     * 打印响应信息
     * @return
     */
    private fun printResponse(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                try {
                    //val text = URLDecoder.decode(message, "utf-8")
                    Timber.e(message)
                } catch (e: UnsupportedEncodingException) {
                    Timber.e(e)
                }
            }

        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * 保存Cookie
     * @return
     */
    private fun saveCookie(): CookieJar =
        object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore[url.host] = cookies
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                val cookies = cookieStore[url.host]
                return cookies ?: ArrayList()
            }
        }

}