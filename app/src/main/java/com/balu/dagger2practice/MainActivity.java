package com.balu.dagger2practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.balu.dagger2practice.car.Car;
import com.balu.dagger2practice.car.Engine;
import com.balu.dagger2practice.car.PetrolEngine;
import com.balu.dagger2practice.dagger.CarComponent;
import com.balu.dagger2practice.dagger.DaggerCarComponent;
import com.balu.dagger2practice.dagger.PetrolEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
//
//    @Inject
//    Car car;

    @Inject
    Engine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for the parameter constructered Module we need to build at the time of component generation.
        CarComponent component = DaggerCarComponent.builder().petrolEngineModule(new PetrolEngineModule(200)).build();
        component.Inject(this);
       // Car myCar = component.getMyCar();
       // car.drive();
        engine.drive();

    }
}
