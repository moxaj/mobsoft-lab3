package mobsoftlab.interactor.chat.events;

import mobsoftlab.model.ChatRoom;

public class AddChatRoomEvent extends AbstractEvent {
    private ChatRoom chatRoom;

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
