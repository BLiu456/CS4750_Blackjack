package com.bignerdranch.android.blackjack;
import android.app.Application;
import android.content.Context;

public class App extends Application{
    private static Context ctx;

    public void onCreate() {
        super.onCreate();
        App.ctx = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.ctx;
    }
}
