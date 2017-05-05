package mobsoftlab.mock;

import mobsoftlab.mock.interceptors.MockInterceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MockHttpServer {
    public static Response call(Request request) {
        return new MockInterceptor().process(request);
    }
}
