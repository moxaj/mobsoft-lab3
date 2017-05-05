package mobsoftlab;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import mobsoftlab.repository.Repository;
import mobsoftlab.ui.UIModule;

public class MobSoftApplication extends Application {
    public static MobSoftApplicationComponent injector;

    @Inject
    Repository repository;

    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        injector = DaggerMobSoftApplicationComponent.builder().uIModule(new UIModule(this)).build();
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    public void setInjector(MobSoftApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
