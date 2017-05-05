package mobsoftlab.repository;

import android.content.Context;

import java.util.List;

import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;

public interface Repository {
    void open(Context context);

    void close();

    List<ChatRoom> getChatRooms();

    void addChatRoom(ChatRoom chatRoom);

    List<ChatMessage> getChatMessages(ChatRoom chatRoom);

    void addChatMessage(ChatRoom chatRoom, ChatMessage chatMessage);
}