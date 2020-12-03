package me.hsgamer.covid19trackerexpansion;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import me.hsgamer.hscore.json.JSONUtils;
import me.hsgamer.hscore.web.UserAgent;
import me.hsgamer.hscore.web.WebUtils;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Utils {
    private Utils() {

    }

    public static JsonObject getJSONObjectFromURL(String url) {
        try {
            JsonValue jsonValue = JSONUtils.getJSON(new InputStreamReader(WebUtils.openConnection(url, UserAgent.FIREFOX).getInputStream()));
            return jsonValue != null && jsonValue.isObject() ? jsonValue.asObject() : null;
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Error when getting data", e);
        }
        return null;
    }

    public static boolean isInvalid(JsonObject jsonObject) {
        JsonValue jsonValue = jsonObject.get("message");
        if (jsonValue != null) {
            Bukkit.getLogger().warning(() -> "Can not get COVID data because: " + jsonValue.toString());
            return true;
        }
        return false;
    }
}
