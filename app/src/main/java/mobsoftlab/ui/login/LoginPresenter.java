package mobsoftlab.ui.login;

import mobsoftlab.ui.Presenter;

public class LoginPresenter extends Presenter<LoginScreen> {
    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void onLoginButtonClicked(String userName) {
        if (!userName.isEmpty()) {
            screen.navigateToRooms(userName);
        }
    }
}