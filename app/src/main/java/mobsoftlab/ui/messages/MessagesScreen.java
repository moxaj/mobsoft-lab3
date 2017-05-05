package mobsoftlab.ui.messages;

import java.util.List;

import mobsoftlab.model.ChatMessage;

public interface MessagesScreen {
    void onMessageSent();

    void showMessages(List<ChatMessage> chatMessages);
}
