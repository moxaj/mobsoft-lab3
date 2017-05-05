package mobsoftlab.interactor.chat;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import mobsoftlab.MobSoftApplication;
import mobsoftlab.interactor.chat.events.AddChatMessageEvent;
import mobsoftlab.interactor.chat.events.GetChatMessagesEvent;
import mobsoftlab.interactor.chat.events.GetChatRoomsEvent;
import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;
import mobsoftlab.network.chat.ChatApi;
import mobsoftlab.repository.Repository;

public class ChatInteractor {
    @Inject
    Repository repository;

    @Inject
    EventBus eventBus;

    @Inject
    ChatApi chatApi;

    public ChatInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getChatRooms() {
        GetChatRoomsEvent event = new GetChatRoomsEvent();

        try {
            // Return from repository?
            List<ChatRoom> chatRooms = chatApi.getChatRooms().execute().body();
            for (ChatRoom chatRoom : chatRooms) {
                repository.addChatRoom(chatRoom);
            }

            event.setChatRooms(chatRooms);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void getChatMessages(ChatRoom chatRoom) {
        GetChatMessagesEvent event = new GetChatMessagesEvent();

        try {
            // Return from repository?
            List<ChatMessage> chatMessages = chatApi.getChatMessages(chatRoom.getName()).execute().body();
            event.setChatMessages(chatMessages);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void addChatMessage(ChatRoom chatRoom, ChatMessage chatMessage) {
        AddChatMessageEvent event = new AddChatMessageEvent();

        try {
            chatApi.postChatMessage(chatRoom.getName(), chatMessage);
            repository.addChatMessage(chatRoom, chatMessage);
            event.setChatRoom(chatRoom);
            event.setChatMessage(chatMessage);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }
}
