package mobsoftlab.interactor;

import dagger.Module;
import dagger.Provides;
import mobsoftlab.interactor.chat.ChatInteractor;

@Module
public class InteractorModule {
    @Provides
    public ChatInteractor provideChat() {
        return new ChatInteractor();
    }
}