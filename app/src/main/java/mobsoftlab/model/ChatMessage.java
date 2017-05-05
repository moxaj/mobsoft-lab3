package mobsoftlab.model;

import com.orm.dsl.Table;

@Table
public class ChatMessage {
    private Long id;
    private String userName;
    private String content;
    private ChatRoom chatRoom;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;

        if (!userName.equals(that.userName)) return false;
        return content.equals(that.content);

    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
}
