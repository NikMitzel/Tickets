package com.example.Ticketsystem.APIs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

public class StreamUtils {
    private static final Gson gson = new Gson();

    public static String readFully(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public static JsonObject readJsonFully(InputStream is) {
        return gson.fromJson(readFully(is), JsonObject.class);
    }

    public static void writeJsonFully(JsonObject json, OutputStream os) {
        try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
            writer.write(gson.toJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}