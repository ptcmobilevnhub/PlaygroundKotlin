package com.example.pyco.toannckotlinassessment.Services

import com.example.pyco.toannckotlinassessment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KLApiServices {

    private var mRetrofit: Retrofit? = null

    init {}

    fun getInstance() : Retrofit {
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
                .baseUrl("http://5b680659629e280014570c80.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return mRetrofit!!
    }

}