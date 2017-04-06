package mobsoftlab;

import android.app.Application;

import mobsoftlab.ui.UIModule;

public class MobSoftApplication extends Application {
    public static MobSoftApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerMobSoftApplicationComponent.builder().uIModule(new UIModule(this)).build();
        injector.inject(this);
    }
}
