package mobsoftlab.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;

public class MemoryRepository implements Repository {
    private Map<ChatRoom, List<ChatMessage>> chatRooms;

    @Override
    public void open(Context context) {
        chatRooms = new HashMap<>();
    }

    @Override
    public void close() {
        chatRooms = null;
    }

    @Override
    public List<ChatRoom> getChatRooms() {
        return new ArrayList<>(chatRooms.keySet());
    }

    @Override
    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.put(chatRoom, new ArrayList<ChatMessage>());
    }

    @Override
    public void removeChatRoom(ChatRoom chatRoom) {
        chatRooms.remove(chatRoom);
    }

    @Override
    public List<ChatMessage> getChatMessages(ChatRoom chatRoom) {
        return chatRooms.get(chatRoom);
    }

    @Override
    public void addChatMessage(ChatRoom chatRoom, ChatMessage chatMessage) {
        List<ChatMessage> chatMessages = chatRooms.get(chatRooms);
        if (chatMessages == null) {
            return;
        }

        chatMessages.add(chatMessage);
    }
}