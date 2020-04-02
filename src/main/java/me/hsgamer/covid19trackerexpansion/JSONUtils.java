package me.hsgamer.covid19trackerexpansion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class JSONUtils {

  private JSONUtils() {
  }

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException {
    URLConnection openConnection = new URL(url).openConnection();
    openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/74.0");
    InputStream is = openConnection.getInputStream();
    BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    String jsonText = readAll(rd);
    return new JSONObject(jsonText);
  }
}