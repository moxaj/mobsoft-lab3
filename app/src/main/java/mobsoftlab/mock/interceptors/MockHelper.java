package mobsoftlab.mock.interceptors;

import java.io.ByteArrayInputStream;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

public class MockHelper {
    public static Response makeResponse(Request request, Headers headers, int code, final String content) {
        return new Response.Builder().protocol(Protocol.HTTP_2).code(code).request(request).headers(headers).body(new ResponseBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/json");
            }

            @Override
            public long contentLength() {
                return content.getBytes().length;
            }

            @Override
            public BufferedSource source() {
                return Okio.buffer(Okio.source(new ByteArrayInputStream(content.getBytes())));
            }
        }).build();
    }
}