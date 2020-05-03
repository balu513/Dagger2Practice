package com.balu.dagger2practice.dagger;

import com.balu.dagger2practice.MainActivity;
import com.balu.dagger2practice.car.Car;
import com.balu.dagger2practice.car.DeiselEngine;
import com.balu.dagger2practice.car.PetrolEngine;
import com.balu.dagger2practice.car.Wheels;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

//https://www.youtube.com/watch?v=3tIvekCTSJg&list=PLrnPJCHvNZuA2ioi4soDZKz8euUQnJW65&index=8


//@ActivityScope
//@Component( dependencies =  {AppComponent.class},  modules = {WheelsModule.class,PetrolEngineModule.class})
//public interface ActivityComponent {
//   // Car getMyCar();
//    void Inject(MainActivity mainActivity);
//
//    @Component.Builder
//    interface Builder{
//
//        @BindsInstance
//        Builder horsePower (@Named( "Horse Power") int horsePower);
//
//        @BindsInstance
//        Builder engineCapcity(@Named("Engine Capacity") int engineCapacity);
//
//        ActivityComponent build();
//
//        Builder appComponent(AppComponent appComponent);
//    }
//}





@ActivityScope
@Subcomponent (modules = {WheelsModule.class,PetrolEngineModule.class})
public interface ActivityComponent {
    // Car getMyCar();
    void Inject(MainActivity mainActivity);

//    @Subcomponent.Builder
//    interface Builder{
//
//        @BindsInstance
//        Builder horsePower (@Named( "Horse Power") int horsePower);
//
//        @BindsInstance
//        Builder engineCapcity(@Named("Engine Capacity") int engineCapacity);
//
//        ActivityComponent build();
//
//       // Builder appComponent(AppComponent appComponent);
//    }




    @Subcomponent.Factory
    interface Factory{
        ActivityComponent create(@BindsInstance @Named( "Horse Power") int horsePower, @BindsInstance @Named("Engine Capacity") int engineCapacity);
    }


}
