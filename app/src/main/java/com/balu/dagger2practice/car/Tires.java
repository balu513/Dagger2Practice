package com.balu.dagger2practice.car;

import android.util.Log;

import javax.inject.Inject;

public class Tires {

    @Inject
    public Tires() {
        Log.d("Costructor", "Tires");
    }
}
