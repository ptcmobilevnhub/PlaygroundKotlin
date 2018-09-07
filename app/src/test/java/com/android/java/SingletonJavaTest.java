package com.android.java;

import org.junit.Test;

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
public class SingletonJavaTest {

    @Test
    public void test_getInstance() {
        SingletonJava singletonJava1 = SingletonJava.getInstance();

        SingletonJava singletonJava2 = SingletonJava.getInstance();
    }
}