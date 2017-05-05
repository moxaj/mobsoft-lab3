package mobsoftlab.model;

import com.orm.dsl.Table;

@Table
public class ChatRoom {
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatRoom chatRoom = (ChatRoom) o;

        return name.equals(chatRoom.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
