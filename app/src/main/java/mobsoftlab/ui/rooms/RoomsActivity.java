package mobsoftlab.ui.rooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import mobsoftlab.MobSoftApplication;
import mobsoftlab.R;
import mobsoftlab.model.ChatRoom;
import mobsoftlab.ui.Extras;
import mobsoftlab.ui.messages.MessagesActivity;

public class RoomsActivity extends AppCompatActivity implements RoomsScreen {
    @Inject
    RoomsPresenter roomsPresenter;

    protected ListView roomsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        MobSoftApplication.injector.inject(this);

        roomsPresenter.setUserName(getIntent().getStringExtra(Extras.EXTRA_USER_NAME));

        roomsListView = (ListView) findViewById(R.id.roomsListView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        roomsPresenter.attachScreen(this);

        roomsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String roomString = (String) roomsListView.getItemAtPosition(position);
                roomsPresenter.onRoomSelected(roomString.substring(0, roomString.indexOf(':')));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        roomsPresenter.detachScreen();
    }

    public void navigateToMessages(String userName, String roomName) {
        Intent intent = new Intent(this, MessagesActivity.class);
        intent.putExtra(Extras.EXTRA_USER_NAME, userName);
        intent.putExtra(Extras.EXTRA_ROOM_NAME, roomName);
        startActivity(intent);
    }

    public void showRooms(List<ChatRoom> chatRooms) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item);
        for (ChatRoom chatRoom : chatRooms) {
            adapter.add(String.format("%s: %s", chatRoom.getName(), chatRoom.getDescription()));
        }

        roomsListView.setAdapter(adapter);
    }
}
