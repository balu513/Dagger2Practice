package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.car.Rims;
import com.balu.dagger2practice.car.Tires;
import com.balu.dagger2practice.car.Wheels;

import dagger.Module;
import dagger.Provides;


@Module
public class WheelsModule {

    @ActivityScope
    @Provides
    public Rims provdeRims(Rims rims){
        return rims;
    }

    @ActivityScope
    @Provides
    public Tires provideTires(Tires tires){
        return tires;
    }

//    @Provides
//    public Wheels provideWheels(Rims rims, Tires tires){
//        return new Wheels(tires,rims);
//    }

    @Provides
    public Wheels provideWheels(Wheels wheels){
        return wheels;
    }


}
