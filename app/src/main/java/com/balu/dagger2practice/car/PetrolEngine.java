package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;

public class PetrolEngine implements Engine {

    int horsePower;

    @Inject
    public PetrolEngine(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public void drive() {
        Log.d("PetrolEngine", "Driving....  &&&   horsePower is: "+horsePower);
    }
}
