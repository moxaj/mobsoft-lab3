package mobsoftlab.ui.messages;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import mobsoftlab.MobSoftApplication;
import mobsoftlab.interactor.chat.ChatInteractor;
import mobsoftlab.interactor.chat.events.GetChatMessagesEvent;
import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;
import mobsoftlab.ui.Presenter;

public class MessagesPresenter extends Presenter<MessagesScreen> {
    @Inject
    ChatInteractor chatInteractor;

    @Inject
    EventBus eventBus;

    private String userName;

    private String roomName;

    private Timer messageTimer;

    @Override
    public void attachScreen(MessagesScreen screen) {
        super.attachScreen(screen);
        MobSoftApplication.injector.inject(this);

        eventBus.register(this);

        messageTimer = new Timer();
        messageTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setName(roomName);
                chatInteractor.getChatMessages(chatRoom);
            }
        }, 0, 1000);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        eventBus.unregister(this);
        messageTimer.cancel();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void onSendButtonClicked(String message) {
        if (!message.isEmpty()) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName(roomName);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setChatRoom(chatRoom);
            chatMessage.setContent(message);
            chatMessage.setUserName(userName);

            chatInteractor.addChatMessage(chatRoom, chatMessage);

            screen.onMessageSent();
        }
    }

    public void onEventMainThread(GetChatMessagesEvent event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            throwable.printStackTrace();
            Log.e("Network", "Error getting chat messages", throwable);
            return;
        }

        screen.showMessages(event.getChatMessages());
    }
}