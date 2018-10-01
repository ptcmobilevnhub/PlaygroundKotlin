package com.pycogroup.duongtran.kotlinassignment.data.api

import com.pycogroup.duongtran.kotlinassignment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var mRetrofit: Retrofit? = null

    init {
        println(">>> RetrofitClient.init")
    }

    fun getInstance() : Retrofit {
        println(">>> RetrofitClient.getInstance begin")

        if(mRetrofit != null) {
            return mRetrofit!!
        }

        val builder = OkHttpClient.Builder()
        // show logs
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.interceptors().add(logging)
        }

        val okHttpClient = builder.build()

        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        println(">>> RetrofitClient.getInstance end")
        return mRetrofit!!
    }
}