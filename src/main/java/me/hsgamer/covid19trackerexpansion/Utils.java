package me.hsgamer.covid19trackerexpansion;

import me.hsgamer.hscore.json.JSONUtils;
import me.hsgamer.hscore.web.UserAgent;
import me.hsgamer.hscore.web.WebUtils;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.logging.Level;

public class Utils {
    private Utils() {

    }

    public static JSONObject getJSONObjectFromURL(String url) {
        try {
            URLConnection connection = WebUtils.openConnection(url, UserAgent.FIREFOX);
            Object o = JSONUtils.getJSON(new InputStreamReader(connection.getInputStream()));
            if (o instanceof JSONObject) {
                return (JSONObject) o;
            }
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Error when getting data", e);
        }
        return null;
    }

    public static boolean validate(JSONObject jsonObject) {
        if (jsonObject.containsKey("message")) {
            Bukkit.getLogger().warning(() -> "Can not get COVID data because: " + jsonObject.get("message"));
            return false;
        }
        return true;
    }
}
