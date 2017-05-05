package mobsoftlab.mock.interceptors;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import mobsoftlab.network.NetworkConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MockInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());
        Log.d("Test Http Client", "URL call: " + uri.toString());

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "chatRoom")) {
            return ChatRoomMock.process(request);
        }

        return MockHelper.makeResponse(request, request.headers(), 404, "Unknown");
    }
}
