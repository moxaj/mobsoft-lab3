package mobsoftlab.mock.interceptors;

import android.net.Uri;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;
import mobsoftlab.network.NetworkConfig;
import mobsoftlab.repository.MemoryRepository;
import mobsoftlab.repository.Repository;
import mobsoftlab.util.GsonHelper;
import okhttp3.Request;
import okhttp3.Response;

public class ChatRoomMock {
    private static final Pattern PATTERN_CHAT_ROOMS =
            Pattern.compile(NetworkConfig.ENDPOINT_PREFIX + "chatRooms");
    private static final Pattern PATTERN_CHAT_MESSAGES =
            Pattern.compile(NetworkConfig.ENDPOINT_PREFIX + "chatRooms/(.*?)/chatMessages");

    public static Response process(Request request) {
        String responseString = "ERROR";
        int responseCode = 503;

        String path = Uri.parse(request.url().toString()).getPath();
        String method = request.method();

        Matcher matcher;
        if ((matcher = PATTERN_CHAT_ROOMS.matcher(path)).matches()) {
            Repository repository = new MemoryRepository();
            repository.open(null);
            responseString = GsonHelper.getJson().toJson(repository.getChatRooms());
            responseCode = 200;
        } else if ((matcher = PATTERN_CHAT_MESSAGES.matcher(path)).matches()) {
            String chatRoomName = matcher.group(1);
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName(chatRoomName);

            Repository repository = new MemoryRepository();
            repository.open(null);

            if (method.equals("POST")) {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setUserName("username");
                chatMessage.setContent("hi there");
                chatMessage.setChatRoom(chatRoom);

                repository.addChatMessage(chatRoom, chatMessage);
                responseString = "OK";
                responseCode = 200;
            } else if (method.equals("GET")) {
                responseString = GsonHelper.getJson().toJson(repository.getChatMessages(chatRoom));
                responseCode = 200;
            }
        }

        return MockHelper.makeResponse(request, request.headers(), responseCode, responseString);
    }
}
