package me.hsgamer.covid19trackerexpansion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class JSONUtils {

  private JSONUtils() {
  }

  private static String readAll(BufferedReader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    while( ( line = rd.readLine() ) != null ) {
      sb.append(line);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String address) throws IOException {
    URL url = new URL(address);
    URLConnection openConnection = url.openConnection();
    openConnection.addRequestProperty("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
    openConnection.connect();
    BufferedReader rd = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
    String jsonText = readAll(rd);
    return new JSONObject(jsonText);
  }
}