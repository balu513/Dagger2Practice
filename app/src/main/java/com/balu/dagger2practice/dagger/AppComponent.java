package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.car.Driver;

import javax.inject.Singleton;

import dagger.Component;

@ApplicationScope
@Component(modules = {DriverModule.class})
public interface AppComponent {

    Driver getDriver();
}
