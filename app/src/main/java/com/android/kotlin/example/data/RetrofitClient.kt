package com.android.kotlin.example.data

import com.android.kotlin.example.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
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
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        println(">>> RetrofitClient.getInstance end")
        return mRetrofit!!
    }
}