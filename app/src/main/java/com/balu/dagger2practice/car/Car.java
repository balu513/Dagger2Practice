package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;

public class Car {

//    @Inject
//    public Car(Engine engine, Wheels wheels) {
//    }

    @Inject
    public Car(){

    }

    public void drive(){
        Log.d("Car class", "Driving");
    }
}
