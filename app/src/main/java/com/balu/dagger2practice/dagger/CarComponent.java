package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.MainActivity;
import com.balu.dagger2practice.car.Car;
import com.balu.dagger2practice.car.DeiselEngine;
import com.balu.dagger2practice.car.PetrolEngine;
import com.balu.dagger2practice.car.Wheels;

import dagger.Component;

//https://www.youtube.com/watch?v=3tIvekCTSJg&list=PLrnPJCHvNZuA2ioi4soDZKz8euUQnJW65&index=8


@Component(modules = {WheelsModule.class,PetrolEngineModule.class})
public interface CarComponent {

   // Car getMyCar();

    void Inject(MainActivity mainActivity);
}
