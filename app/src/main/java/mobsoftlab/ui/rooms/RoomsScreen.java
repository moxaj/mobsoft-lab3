package mobsoftlab.ui.rooms;

import java.util.List;

import mobsoftlab.model.ChatRoom;

public interface RoomsScreen {
    void showRooms(List<ChatRoom> chatRooms);

    void navigateToMessages(String userName, String roomName);
}
