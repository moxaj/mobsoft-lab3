package mobsoftlab.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;

public class SugarOrmRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<ChatRoom> getChatRooms() {
        return SugarRecord.listAll(ChatRoom.class);
    }

    @Override
    public void addChatRoom(ChatRoom chatRoom) {
        SugarRecord.saveInTx(chatRoom);
    }

    @Override
    public void removeChatRoom(ChatRoom chatRoom) {
        SugarRecord.deleteInTx(chatRoom);
    }

    @Override
    public List<ChatMessage> getChatMessages(ChatRoom chatRoom) {
        return SugarRecord.find(ChatMessage.class, "chatRoom = ?", chatRoom.getId().toString());
    }

    @Override
    public void addChatMessage(ChatRoom chatRoom, ChatMessage chatMessage) {
        chatMessage.setChatRoom(chatRoom);
        SugarRecord.saveInTx(chatMessage);
    }
}