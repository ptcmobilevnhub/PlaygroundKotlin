package com.android.kotlin.example

import org.junit.Test

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class MainTestExample {

    @Test
    fun main(){
        print("sum of 3 and 5 is ")
        //println(sum(3,5))
        println(sum1(3,5))

        printSum(8, 9)
    }

    //------------- test function ------------//

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum1(a: Int, b: Int) = a + b

    fun printSum(a: Int, b : Int){
        print("sum of ${a} and ${b} is ${a + b}")
    }
}