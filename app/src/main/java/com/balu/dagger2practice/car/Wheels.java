package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;

public class Wheels {

    public Tires tires;

    public Rims rims;

    @Inject
    public Wheels(Tires tires, Rims rims) {
        Log.d("Costructor", "Wheels (tires, rims)");
        this.tires = tires;
        this.rims = rims;
    }
}
