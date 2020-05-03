package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;

@Module
public class DeiselEngine implements Engine {

    int horsePower;

    int engineCapacity;

    @Inject
    public DeiselEngine(int horsePower, int engineCapacity){
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }

//    @Inject
//    public DeiselEngine(@Named("Horse Power") int horsePower, @Named("Engine Capacity") int engineCapacity){
//        this.horsePower = horsePower;
//        this.engineCapacity = engineCapacity;
//    }

    @Override
    public void drive() {
        Log.d("DeiselEngine","Driving ....  horse power "+horsePower+"   Engine Capacity: "+engineCapacity);
    }
}
