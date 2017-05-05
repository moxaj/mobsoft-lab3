package mobsoftlab.ui.rooms;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import mobsoftlab.MobSoftApplication;
import mobsoftlab.interactor.chat.ChatInteractor;
import mobsoftlab.interactor.chat.events.GetChatRoomsEvent;
import mobsoftlab.ui.Presenter;

public class RoomsPresenter extends Presenter<RoomsScreen> {
    @Inject
    ChatInteractor chatInteractor;

    @Inject
    EventBus eventBus;

    @Inject
    Executor executor;

    private String userName;

    @Override
    public void attachScreen(RoomsScreen screen) {
        super.attachScreen(screen);
        MobSoftApplication.injector.inject(this);

        eventBus.register(this);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatInteractor.getChatRooms();
            }
        });
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        eventBus.unregister(this);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void onRoomSelected(String roomName) {
        screen.navigateToMessages(userName, roomName);
    }

    public void onEventMainThread(GetChatRoomsEvent event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            throwable.printStackTrace();
            Log.e("Network", "Error getting chat rooms", throwable);
            return;
        }

        screen.showRooms(event.getChatRooms());
    }
}
