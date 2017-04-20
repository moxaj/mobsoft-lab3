package mobsoftlab.ui.rooms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import mobsoftlab.MobSoftApplication;
import mobsoftlab.R;

public class RoomsActivity extends AppCompatActivity implements RoomsScreen {
    @Inject
    RoomsPresenter roomsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        roomsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        roomsPresenter.detachScreen();
    }
}
