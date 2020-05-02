package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;

import dagger.Module;

@Module
public class DeiselEngine implements Engine {

    @Inject
    public DeiselEngine(){
    }

    @Override
    public void drive() {
        Log.d("DeiselEngine","Driving ....");
    }
}
