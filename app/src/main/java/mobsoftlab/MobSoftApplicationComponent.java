package mobsoftlab;

import javax.inject.Singleton;

import dagger.Component;
import mobsoftlab.ui.UIModule;
import mobsoftlab.ui.main.MainActivity;
import mobsoftlab.ui.main.MainPresenter;

@Singleton
@Component(modules = {UIModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(MobSoftApplication mobSoftApplication);

    void inject(MainPresenter mainPresenter);
}
