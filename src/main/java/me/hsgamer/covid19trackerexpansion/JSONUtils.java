package me.hsgamer.covid19trackerexpansion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

  private JSONUtils() {
  }

  private static String readAll(BufferedReader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String address) {
    String jsonText;
    try {
      URL url = new URL(address);
      URLConnection openConnection = url.openConnection();
      openConnection.addRequestProperty("User-Agent",
          "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
      openConnection.connect();
      BufferedReader rd = new BufferedReader(
          new InputStreamReader(openConnection.getInputStream()));
      jsonText = readAll(rd);
    } catch (IOException e) {
      if (Main.isDebug()) {
        PlaceholderAPIPlugin.getInstance().getLogger()
            .log(Level.WARNING, e, () -> "Error when receiving value");
      }
      return null;
    }

    if (Main.isDebug()) {
      PlaceholderAPIPlugin.getInstance().getLogger().info(() -> "Received value: " + jsonText);
    }

    try {
      return new JSONObject(jsonText);
    } catch (JSONException e) {
      if (Main.isDebug()) {
        PlaceholderAPIPlugin.getInstance().getLogger()
            .log(Level.WARNING, e, () -> "Error when creating JSON object");
      }
      return null;
    }
  }
}