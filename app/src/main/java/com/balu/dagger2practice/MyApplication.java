package com.balu.dagger2practice;

import android.app.Application;

import com.balu.dagger2practice.dagger.AppComponent;
import com.balu.dagger2practice.dagger.DaggerAppComponent;
import com.balu.dagger2practice.dagger.DriverModule;

public class MyApplication extends Application {
    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(new DriverModule("BALU"));
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }
}
