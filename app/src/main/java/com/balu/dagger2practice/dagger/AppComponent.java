package com.balu.dagger2practice.dagger;

import org.xmlpull.v1.sax2.Driver;

import javax.inject.Singleton;

import dagger.Component;

@ApplicationScope
@Component(modules = {DriverModule.class})
public interface AppComponent {
   // Driver getDriver();


   // ActivityComponent.Builder getActivityComponentBuilder();

    ActivityComponent.Factory getActivityComponentFactory();

    @Component.Factory
    public interface  Factory{

        AppComponent create(DriverModule driverModule);
    }



}
