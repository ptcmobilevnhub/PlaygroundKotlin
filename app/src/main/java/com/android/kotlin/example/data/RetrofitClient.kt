package com.android.kotlin.example.data

import com.android.kotlin.example.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
class RetrofitClient private constructor(){

    private var mRetrofit: Retrofit? = null

    init {
        println(">>> RetrofitClient -> init @" + hashCode())

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
    }

    private object Holder {
        val INSTANCE = RetrofitClient()
    }

    companion object {
        @JvmStatic
        fun getInstance(): Retrofit {
            println(">>> getInstance " + this.javaClass.simpleName + " @" + this.hashCode())
            return Holder.INSTANCE.mRetrofit!!
        }
    }



    /*fun getInstance(): Retrofit {
        if (mRetrofit == null) {

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
        }

        return mRetrofit!!
    }*/
}