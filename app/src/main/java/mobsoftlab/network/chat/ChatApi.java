package mobsoftlab.network.chat;

import mobsoftlab.model.ChatMessage;
import mobsoftlab.model.ChatRoom;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ChatApi {

    /**
     * Gets all the chat rooms
     *
     * @return Call<List<ChatRoom>>
     */
    @GET("chatRooms")
    Call<List<ChatRoom>> getChatRooms();


    /**
     * Gets all the chat messages for a given chat room
     *
     * @param chatRoomName The name of the chat room
     * @return Call<List<ChatMessage>>
     */
    @GET("chatRooms/{chatRoomName}/chatMessages")
    Call<List<ChatMessage>> getChatMessages(
            @Path("chatRoomName") String chatRoomName
    );


    /**
     * Sends a new chat message to the given chat room
     *
     * @param chatRoomName The name of the chat room
     * @param chatMessage  The chat message
     * @return Call<List<ChatMessage>>
     */
    @POST("chatRooms/{chatRoomName}/chatMessages")
    Call<List<ChatMessage>> postChatMessage(
            @Path("chatRoomName") String chatRoomName, @Body ChatMessage chatMessage
    );
}