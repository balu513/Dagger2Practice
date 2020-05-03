

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

------------------------------------------------------------------------------------------------------------------------------

@Singleton :  singleton object per Component , Not for entire project .so whenver Component re-created, @singleton obj ref will change.
so if you build component inside activity then fot entire App we can maintian same @Singleton Obj.

if you want Application level Singleton objects we need to define in oncreate() of Application class.

------------------------------------------------------------------------------------------------------------------------------



