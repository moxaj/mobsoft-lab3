package mobsoftlab.ui.messages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import mobsoftlab.MobSoftApplication;
import mobsoftlab.R;

public class MessagesActivity extends AppCompatActivity implements MessagesScreen {
    @Inject
    MessagesPresenter messagesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        messagesPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        messagesPresenter.detachScreen();
    }
}
