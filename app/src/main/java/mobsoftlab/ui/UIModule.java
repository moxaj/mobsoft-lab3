package mobsoftlab.ui;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import mobsoftlab.ui.login.LoginPresenter;
import mobsoftlab.ui.messages.MessagesPresenter;
import mobsoftlab.ui.rooms.RoomsPresenter;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(1);
    }

    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    @Singleton
    public RoomsPresenter provideRoomsPresenter() {
        return new RoomsPresenter();
    }

    @Provides
    @Singleton
    public MessagesPresenter provideMessagesPresenter() {
        return new MessagesPresenter();
    }
}
