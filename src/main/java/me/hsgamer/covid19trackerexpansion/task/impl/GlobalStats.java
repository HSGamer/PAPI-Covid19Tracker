package me.hsgamer.covid19trackerexpansion.task.impl;

import me.hsgamer.covid19trackerexpansion.JSONUtils;
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
    JSONObject jsonObject = JSONUtils.readJsonFromUrl(URL);
    if (jsonObject != null) {
      setValue(jsonObject.getJSONArray("results").getJSONObject(0));
    }
  }
}