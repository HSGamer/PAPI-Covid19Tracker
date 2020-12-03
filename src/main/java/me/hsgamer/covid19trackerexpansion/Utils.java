package me.hsgamer.covid19trackerexpansion;

import me.hsgamer.hscore.web.UserAgent;
import me.hsgamer.hscore.web.WebUtils;
import org.bukkit.Bukkit;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.logging.Level;

public class Utils {
    private Utils() {

    }

    private static String readAll(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    public static JSONObject getJSONObjectFromURL(String url) {
        try {
            URLConnection connection = WebUtils.openConnection(url, UserAgent.FIREFOX);
            return new JSONObject(readAll(connection.getInputStream()));
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Error when getting data", e);
        }
        return null;
    }

    public static boolean isInvalid(JSONObject jsonObject) {
        if (jsonObject.has("message")) {
            Bukkit.getLogger().warning(() -> "Can not get COVID data because: " + jsonObject.getString("message"));
            return true;
        }
        return false;
    }
}
