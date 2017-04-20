package mobsoftlab.interactor.chat;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import mobsoftlab.MobSoftApplication;
import mobsoftlab.interactor.chat.events.AddChatMessageEvent;
import mobsoftlab.interactor.chat.events.AddChatRoomEvent;
import mobsoftlab.interactor.chat.events.GetChatMessagesEvent;
import mobsoftlab.interactor.chat.events.GetChatRoomsEvent;
import mobsoftlab.interactor.chat.events.RemoveChatRoomEvent;
import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;
import mobsoftlab.repository.Repository;

public class ChatInteractor {
    @Inject
    Repository repository;

    @Inject
    EventBus eventBus;

    public ChatInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getChatRooms() {
        GetChatRoomsEvent event = new GetChatRoomsEvent();

        try {
            List<ChatRoom> chatRooms = repository.getChatRooms();
            event.setChatRooms(chatRooms);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void addChatRoom(ChatRoom chatRoom) {
        AddChatRoomEvent event = new AddChatRoomEvent();

        try {
            repository.addChatRoom(chatRoom);
            event.setChatRoom(chatRoom);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void removeChatRoom(ChatRoom chatRoom) {
        RemoveChatRoomEvent event = new RemoveChatRoomEvent();

        try {
            repository.removeChatRoom(chatRoom);
            event.setChatRoom(chatRoom);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void getChatMessages(ChatRoom chatRoom) {
        GetChatMessagesEvent event = new GetChatMessagesEvent();

        try {
            List<ChatMessage> chatMessages = repository.getChatMessages(chatRoom);
            event.setChatMessages(chatMessages);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void addChatMessage(ChatRoom chatRoom, ChatMessage chatMessage) {
        AddChatMessageEvent event = new AddChatMessageEvent();

        try {
            repository.addChatMessage(chatRoom, chatMessage);
            event.setChatRoom(chatRoom);
            event.setChatMessage(chatMessage);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }
}
