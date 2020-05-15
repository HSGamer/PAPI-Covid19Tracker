package me.hsgamer.covid19trackerexpansion.task.impl;

import me.hsgamer.covid19trackerexpansion.JSONUtils;
import me.hsgamer.covid19trackerexpansion.task.Stats;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

public class CountryStats extends Stats {

  private final String url;

  public CountryStats(JavaPlugin plugin, String country) {
    super(plugin);
    url = "https://thevirustracker.com/free-api?countryTotal=" + country;
  }

  @Override
  public void run() {
    JSONObject jsonObject = JSONUtils.readJsonFromUrl(url);
    if (jsonObject != null) {
      setValue(jsonObject.getJSONArray("countrydata").getJSONObject(0));
    }
  }
}
