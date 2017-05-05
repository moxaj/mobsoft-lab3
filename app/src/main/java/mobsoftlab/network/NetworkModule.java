package mobsoftlab.network;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mobsoftlab.network.chat.ChatApi;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }

    @Provides
    @Singleton
    public ChatApi provideChatRoomApi(Retrofit retrofit) {
        return retrofit.create(ChatApi.class);
    }
}