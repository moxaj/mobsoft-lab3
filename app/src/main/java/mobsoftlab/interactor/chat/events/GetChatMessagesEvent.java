package mobsoftlab.interactor.chat.events;

import java.util.List;

import mobsoftlab.model.ChatMessage;

public class GetChatMessagesEvent extends AbstractEvent {
    List<ChatMessage> chatMessages;

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
