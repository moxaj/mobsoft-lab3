package mobsoftlab;

import javax.inject.Singleton;

import dagger.Component;
import mobsoftlab.interactor.chat.ChatInteractor;
import mobsoftlab.ui.UIModule;
import mobsoftlab.ui.login.LoginActivity;
import mobsoftlab.ui.login.LoginPresenter;
import mobsoftlab.ui.messages.MessagesActivity;
import mobsoftlab.ui.messages.MessagesPresenter;
import mobsoftlab.ui.rooms.RoomsActivity;
import mobsoftlab.ui.rooms.RoomsPresenter;

@Singleton
@Component(modules = {UIModule.class})
public interface MobSoftApplicationComponent {
    void inject(MobSoftApplication mobSoftApplication);

    void inject(ChatInteractor chatInteractor);

    void inject(LoginActivity loginActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(RoomsActivity roomsActivity);

    void inject(RoomsPresenter roomsPresenter);

    void inject(MessagesActivity messagesActivity);

    void inject(MessagesPresenter messagesPresenter);
}
