package mobsoftlab.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
    public static Gson getJson() {
        return new GsonBuilder().setLenient().create();
    }
}
