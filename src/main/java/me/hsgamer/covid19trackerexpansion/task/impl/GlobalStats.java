package me.hsgamer.covid19trackerexpansion.task.impl;

import java.io.IOException;
import java.util.logging.Level;
import me.hsgamer.covid19trackerexpansion.JSONUtils;
import me.hsgamer.covid19trackerexpansion.Main;
import me.hsgamer.covid19trackerexpansion.task.Stats;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

public class GlobalStats extends Stats {

  private static final String URL = "https://thevirustracker.com/free-api?global=stats";

  public GlobalStats(JavaPlugin plugin) {
    super(plugin);
  }

  @Override
  public void run() {
    try {
      JSONObject jsonObject = JSONUtils.readJsonFromUrl(URL);
      if (jsonObject != null) {
        setValue(jsonObject.getJSONArray("results").getJSONObject(0));
      }
    } catch (IOException e) {
      if (Main.isDebug()) {
        plugin.getLogger().log(Level.WARNING, "Error when fetching data", e);
      }
    }
  }
}