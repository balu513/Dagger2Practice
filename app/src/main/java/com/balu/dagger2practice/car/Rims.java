package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;

public class Rims {

    @Inject
    public Rims() {
        Log.d("Costructor", "Rims");
    }
}
