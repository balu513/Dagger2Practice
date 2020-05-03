

------------------------------------------------------------------------------------------------------------------------------

**** @Component :  Component it contain modules , dependencies and subcomponents and component.Builder etc...

**** Inject we can do by feild, constructor and method..(Method mostly we never use)

***  @Inject if u give to constructor then we can inject that object anywhere this case no need @provides But if u use 3rd party libraires
     we dont have access to add @Inject to their classes so we have to use @provides and return the reff whatevr we want.

*** @provides have to be in @Module and that module need to be add to component.

**** @binds and @provides both are to provide to particular object.  in case @provides we can customise object means we can write implenention
     inside method , coming to @binds no method implementation it returns object asteise.



*** Pass parameter values to Dagger from our UI

1st WAY:------------------------------------------------------------------------------------------------------------------------------

public class PetrolEngine implements Engine {
    int horsePower;
    @Inject
    public PetrolEngine(int horsePower) {
        this.horsePower = horsePower;
    }
 }

inside Module:
@Module
public class PetrolEngineModule {
    int horsePower;
    public PetrolEngineModule(int horsePower) {
        this.horsePower = horsePower;
    }
    @Provides
    public int provideHorsePower(){
        return horsePower;
    }
    @Provides
    public Engine provideEngine(PetrolEngine engine){
        return engine;
    }
 }

 inside Activity:
 CarComponent component = DaggerCarComponent.builder().petrolEngineModule(new PetrolEngineModule(200)).build();

 Above Statement we are passing paramter value to module by constructor. return int value @provides will pass to PetrolEngine constructor.


2nd WAY:  ------------------------------------------------------------------------------------------------------------------------------

public class DeiselEngine implements Engine {
    int horsePower;
    int engineCapacity;
    @Inject
    public DeiselEngine(@Named("Horse Power") int horsePower, @Named("Engine Capacity") int engineCapacity){
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }
 }

 inside module:
 @Module
 public abstract class DeiselEngineModule {

     @Provides
     public Engine provideDeiselEngine(DeiselEngine engine);
 }

inside component: [[(We have define our own component builder and pass the required values and if u add any dependecies we need handle)
@Component(modules = {WheelsModule.class,PetrolEngineModule.class})
public interface CarComponent {
   // Car getMyCar();
    void Inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder horsePower (@Named( "Horse Power") int horsePower);
        @BindsInstance
        Builder engineCapcity(@Named("Engine Capacity") int engineCapacity);

        CarComponent build();
    }
  }

inside Activity:
CarComponent component = DaggerCarComponent.builder().horsePower(444).engineCapcity(5400).build();

Now we can inject whatever refrence we want. like below
    @Inject
    Car car;

    @Inject
    Engine engine;



SCOPES:  (perActivity && perApplication)
------------------------------------------------------------------------------------------------------------------------------

@Singleton :  singleton object per Component , Not for entire project .so whenver Component re-created, @singleton obj ref will change.
so if you build component inside activity then fot entire App we can maintian same @Singleton Obj.

if you want Application level Singleton objects we need to define in oncreate() of Application class.

----------------   ---------

Singleton is per component base, in some case we need few references in Activity level so we should keep that in @ActityScope.
means need to create component inside onCreate() Activity, so when Activity re-created Component also recreates.
in this situation if we need few Objects in Application level Singleton (eg: HttpEnitity obj) so what we need to do is

Create one more component with ApplicationScope and need to initiate that component in onCreate() of Application.

and we have to add that ApplicationComponent to ActivityComponen as Dependency.


like below(inside Activity onCreate()),
 ActivityComponent activityComponent = DaggerActivityComponent.builder().appComponent(((MyApplication) getApplication()).getAppComponent()).engineCapcity(200).
                horsePower(234).build();
        activityComponent.Inject(this);


------------
@ActivityScope
@Component( dependencies =  {AppComponent.class},  modules = {WheelsModule.class,PetrolEngineModule.class})
public interface ActivityComponent {
   // Car getMyCar();
    void Inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder horsePower (@Named( "Horse Power") int horsePower);

        @BindsInstance
        Builder engineCapcity(@Named("Engine Capacity") int engineCapacity);

        ActivityComponent build();

        Builder appComponent(AppComponent appComponent);
    }
}
-------------

@ApplicationScope
@Component(modules = {DriverModule.class})
public interface AppComponent {
    Driver getDriver();
}
-----------
inside Application class:
public class MyApplication extends Application {
    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }

---------
inside Activity Injected Values:

     @Inject
     Car car;

     @Inject
     PetrolEngine engine;

     @Inject
     Driver driver;

=> driver --- belongs to Application component so ref wont change even new ActivityCompent creates while Activity recreated.
=> car, engine -- belongs to Activity component so ref will change even new ActivityCompent creates while Activity recreates.


NOTE:  through dependency Components, need to explicity provid object then only will avalible in the above code base AppComponet providing
driver object by getDriver(), so if we have more objects for every obj we have provide explicitly, to overcome this problem SubComponent
concept came.

SubComponent Access all parentComponent Objects with out Explicit return methods like 'Driver gerDriver()'.

------------------------------------------------------------------------------------------------------------------------------

SubComponents:

inside Activity:

        ActivityComponent activityComponent = ((MyApplication)getApplication()).getAppComponent().getActivityComponentBuilder()
                .engineCapcity(1200)
                .horsePower(450)
                .build();
        activityComponent.Inject(this);

-----
@ActivityScope
@Subcomponent (modules = {WheelsModule.class,PetrolEngineModule.class})
public interface ActivityComponent {
    // Car getMyCar();
    void Inject(MainActivity mainActivity);

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        Builder horsePower (@Named( "Horse Power") int horsePower);

        @BindsInstance
        Builder engineCapcity(@Named("Engine Capacity") int engineCapacity);

        ActivityComponent build();

       // Builder appComponent(AppComponent appComponent);
    }
}
--------
@ApplicationScope
@Component(modules = {DriverModule.class})
public interface AppComponent {
   // Driver getDriver();
    ActivityComponent.Builder getActivityComponentBuilder();
}

--------------------------------------------------------------

@component.Builder vs @Component.Factory

@component.Builder -> passing the values at the time of component creation in builder way chance of missing some values, it prefer if some values are optionals.
@Component.Factory  -> we have to pass all the values at the time of component creation.

