package mobsoftlab.interactor.chat.events;

import java.util.List;

import mobsoftlab.model.ChatRoom;

public class GetChatRoomsEvent extends AbstractEvent {
    List<ChatRoom> chatRooms;

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }
}
