package mobsoftlab.interactor.chat.events;

import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;

public class AddChatMessageEvent extends AbstractEvent {
    private ChatRoom chatRoom;
    private ChatMessage chatMessage;

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }
}
