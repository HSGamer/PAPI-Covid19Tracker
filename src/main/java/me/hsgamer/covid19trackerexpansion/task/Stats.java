package me.hsgamer.covid19trackerexpansion.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONObject;

public abstract class Stats extends BukkitRunnable {

  protected int cases = 0;
  protected int recovered = 0;
  protected int unresolved = 0;
  protected int deaths = 0;
  protected int newCases = 0;
  protected int newDeaths = 0;
  protected int activeCases = 0;
  protected int seriousCases = 0;
  protected JavaPlugin plugin;

  public Stats(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public int getCases() {
    return cases;
  }

  public int getRecovered() {
    return recovered;
  }

  public int getUnresolved() {
    return unresolved;
  }

  public int getDeaths() {
    return deaths;
  }

  public int getNewCases() {
    return newCases;
  }

  public int getNewDeaths() {
    return newDeaths;
  }

  public int getActiveCases() {
    return activeCases;
  }

  public int getSeriousCases() {
    return seriousCases;
  }

  public void setValue(JSONObject data) {
    cases = data.getInt("total_cases");
    recovered = data.getInt("total_recovered");
    unresolved = data.getInt("total_unresolved");
    deaths = data.getInt("total_deaths");
    newCases = data.getInt("total_new_cases_today");
    newDeaths = data.getInt("total_new_deaths_today");
    activeCases = data.getInt("total_active_cases");
    seriousCases = data.getInt("total_serious_cases");
  }
}
