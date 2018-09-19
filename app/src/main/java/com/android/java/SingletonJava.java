package com.android.java;

/**
 * Created by Vinh.Tran on 9/7/18.
 **/
public class SingletonJava {
    private static final SingletonJava ourInstance = new SingletonJava();

    public static SingletonJava getInstance() {
        System.out.println("getInstance @" + ourInstance.hashCode());
        return ourInstance;
    }

    private SingletonJava() {
    }
}
