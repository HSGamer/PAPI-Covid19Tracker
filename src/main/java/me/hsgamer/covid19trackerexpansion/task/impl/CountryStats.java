package me.hsgamer.covid19trackerexpansion.task.impl;

import java.io.IOException;
import java.util.logging.Level;
import me.hsgamer.covid19trackerexpansion.JSONUtils;
import me.hsgamer.covid19trackerexpansion.task.Stats;
import org.bukkit.plugin.java.JavaPlugin;

public class CountryStats extends Stats {

  private final String url;

  public CountryStats(JavaPlugin plugin, String country) {
    super(plugin);
    url = "https://thevirustracker.com/free-api?countryTotal=" + country;
  }

  @Override
  public void run() {
    try {
      setValue(JSONUtils.readJsonFromUrl(url).getJSONArray("countrydata").getJSONObject(0));
    } catch (IOException e) {
      plugin.getLogger().log(Level.WARNING, "Error when fetching data", e);
    }
  }
}
