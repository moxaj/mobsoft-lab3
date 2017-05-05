package mobsoftlab;

import android.app.Application;

import javax.inject.Inject;

import mobsoftlab.repository.Repository;
import mobsoftlab.ui.UIModule;

public class MobSoftApplication extends Application {
    public static MobSoftApplicationComponent injector;

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerMobSoftApplicationComponent.builder().uIModule(new UIModule(this)).build();
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    public void setInjector(MobSoftApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }
}
