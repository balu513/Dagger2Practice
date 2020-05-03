package com.balu.dagger2practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.balu.dagger2practice.car.Car;
import com.balu.dagger2practice.car.Driver;
import com.balu.dagger2practice.car.PetrolEngine;
import com.balu.dagger2practice.dagger.ActivityComponent;
import com.balu.dagger2practice.dagger.DaggerActivityComponent;
import com.balu.dagger2practice.dagger.PetrolEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
//
    @Inject
    Car car;

    @Inject
    PetrolEngine engine;

    @Inject
    Driver driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","onCreate");



        // AppComponent is Application Level singleton so 'driver' obj refrence not changing even if Activity orientation change.
        // ActivityComponent is Activity level singleton so , 'engine' obj ref is changing upon orientation change.


        // for the parameter constructered Module we need to build at the time of component generation.
       // ActivityComponent component = DaggerActivityComponent.builder().appComponent(((MyApplication)getApplication()).getAppComponent()).petrolEngineModule(new PetrolEngineModule(200)).build();



        // Binds to BindingInstance of Component Builder
        ActivityComponent activityComponent = DaggerActivityComponent.builder().appComponent(((MyApplication) getApplication()).getAppComponent()).engineCapcity(200).
                horsePower(234).build();
        activityComponent.Inject(this);



       // Car myCar = component.getMyCar();
        car.drive();
        engine.drive();

        Log.d("MainActivity", "\ncar: "+car+"\nengine: "+engine +"\ndriver: "+driver);

    }
}
