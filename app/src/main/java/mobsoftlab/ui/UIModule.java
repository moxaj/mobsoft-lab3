package mobsoftlab.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
