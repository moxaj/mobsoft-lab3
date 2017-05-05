package mobsoftlab.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import mobsoftlab.MobSoftApplication;
import mobsoftlab.R;
import mobsoftlab.ui.Extras;
import mobsoftlab.ui.rooms.RoomsActivity;

public class LoginActivity extends AppCompatActivity implements LoginScreen {
    @Inject
    LoginPresenter loginPresenter;

    protected EditText userNameEditText;

    protected Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MobSoftApplication.injector.inject(this);

        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLoginButtonClicked(userNameEditText.getText().toString());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    public void navigateToRooms(String userName) {
        Intent intent = new Intent(this, RoomsActivity.class);
        intent.putExtra(Extras.EXTRA_USER_NAME, userName);
        startActivity(intent);
    }
}
