package mobsoftlab.ui.main;

import mobsoftlab.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        screen.showMessage("Hi there!");
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}
