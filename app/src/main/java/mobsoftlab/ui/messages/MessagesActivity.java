package mobsoftlab.ui.messages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import mobsoftlab.MobSoftApplication;
import mobsoftlab.R;
import mobsoftlab.model.ChatMessage;
import mobsoftlab.ui.Extras;

public class MessagesActivity extends AppCompatActivity implements MessagesScreen {
    @Inject
    MessagesPresenter messagesPresenter;

    protected ListView messagesListView;

    protected EditText messageEditText;

    protected Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        MobSoftApplication.injector.inject(this);

        Intent intent = getIntent();
        messagesPresenter.setUserName(intent.getStringExtra(Extras.EXTRA_USER_NAME));
        messagesPresenter.setRoomName(intent.getStringExtra(Extras.EXTRA_ROOM_NAME));

        messagesListView = (ListView) findViewById(R.id.messagesListView);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        messagesPresenter.attachScreen(this);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagesPresenter.onSendButtonClicked(messageEditText.getText().toString());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        messagesPresenter.detachScreen();
    }

    public void onMessageSent() {
        messageEditText.setText("");
    }

    public void showMessages(List<ChatMessage> chatMessages) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item);
        for (ChatMessage chatMessage : chatMessages) {
            adapter.add(String.format("%s: %s", chatMessage.getUserName(), chatMessage.getContent()));
        }

        messagesListView.setAdapter(adapter);
    }
}
