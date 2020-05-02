package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.car.DeiselEngine;
import com.balu.dagger2practice.car.Engine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DeiselEngineModule {

    @Binds
    abstract Engine provideDeiselEngine(DeiselEngine engine);
}
