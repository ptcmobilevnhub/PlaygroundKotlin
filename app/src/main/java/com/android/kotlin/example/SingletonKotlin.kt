package com.android.kotlin.example

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
class SingletonKotlin private constructor(){

    init {
        // define in constructor
        println(">>> SingletonKotlin -> init")

    }

    private object Holder {

        val instance = SingletonKotlin()

        fun foo() {
            println(">>> SingletonKotlin -> Holder foo")
        }
    }

    companion object {

        @JvmStatic
        fun getInstance(): SingletonKotlin {
            println(">>> SingletonKotlin getInstance @" + hashCode())
            Holder.foo()
            return Holder.instance
        }
    }

}