package com.unocinco;
import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication singleton;


// Возвращает экземпляр данного класса

    public static MyApplication getInstance() {

        return singleton;

    }

    @Override

    public final void onCreate() {

        super.onCreate();

        singleton = this;

    }

}