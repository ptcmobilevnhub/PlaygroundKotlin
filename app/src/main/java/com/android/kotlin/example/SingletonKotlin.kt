package com.android.kotlin.example

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
class SingletonKotlin private constructor(){

    init {
        // define in constructor
        println("SingletonKotlin -> init")
    }

    private object Holder {

        val INSTANCE = SingletonKotlin()
        fun print() {
            println("SingletonKotlin -> Holder print")
        }
    }

    companion object {

        @JvmStatic
        fun getInstance(): SingletonKotlin {
            println(">>> getInstance @" + hashCode())
            return Holder.INSTANCE
        }
    }

}