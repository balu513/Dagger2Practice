package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.car.Driver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DriverModule {

    @ApplicationScope
    @Provides
    public Driver provideDriver(){
        return new Driver();
    }
}
