package com.balu.dagger2practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.balu.dagger2practice.car.Car;
import com.balu.dagger2practice.car.Driver;
import com.balu.dagger2practice.car.PetrolEngine;
import com.balu.dagger2practice.dagger.ActivityComponent;
import com.balu.dagger2practice.dagger.AppComponent;
import com.balu.dagger2practice.dagger.PetrolEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

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



//         Bind values to BindingInstance of Component Builder
//         (Component dependency)
//        ActivityComponent activityComponent = DaggerActivityComponent.builder().appComponent(((MyApplication) getApplication()).getAppComponent()).engineCapcity(200).
//                horsePower(234).build();
//        activityComponent.Inject(this);



        //SubComponent =>>>>>   ActivityComponet is SubComponent for AppComponent here.
        ActivityComponent activityComponent = ((MyApplication)getApplication()).getAppComponent().getActivityComponentBuilder()
                .engineCapcity(1200)
                .horsePower(450)
                .build();
        activityComponent.Inject(this);




        car.drive();
        engine.drive();

        Log.d("MainActivity", "\ncar: "+car+"\nengine: "+engine +"\ndriver: "+driver);

    }
}
