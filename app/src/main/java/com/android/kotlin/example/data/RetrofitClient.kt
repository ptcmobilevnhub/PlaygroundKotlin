package com.android.kotlin.example.data

import com.android.kotlin.example.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
class RetrofitClient {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://api.github.com/"

    fun getInstance(): Retrofit {
        if (retrofit == null) {

            val builder = OkHttpClient.Builder()
            // show logs
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                builder.interceptors().add(logging)
            }

            val okHttpClient = builder.build()

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
        }

        return retrofit!!
    }
}