package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.car.Engine;
import com.balu.dagger2practice.car.PetrolEngine;

import dagger.Module;
import dagger.Provides;

@Module
public class PetrolEngineModule {

    int horsePower;
//
//    public PetrolEngineModule(int horsePower) {
//        this.horsePower = horsePower;
//    }
//
//    @Provides
//    public int provideHorsePower(){
//        return horsePower;
//    }
//
//    @Provides
//    public Engine provideEngine(PetrolEngine engine){
//        return engine;
//    }

    @Provides
    public Engine provdeEngine(){
        return new PetrolEngine(horsePower);
    }
}
