package me.hsgamer.covid19trackerexpansion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Taskable;
import me.hsgamer.covid19trackerexpansion.task.Stats;
import me.hsgamer.covid19trackerexpansion.task.impl.CountryStats;
import me.hsgamer.covid19trackerexpansion.task.impl.GlobalStats;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public final class Main extends PlaceholderExpansion implements
    Configurable, Taskable {

  private final Map<String, Stats> statsMap = new CaseInsensitiveStringMap<>();
  private final List<BukkitTask> tasks = new ArrayList<>();
  private final String version = getClass().getPackage().getImplementationVersion();

  @Override
  public boolean canRegister() {
    return true;
  }

  @Override
  public String getIdentifier() {
    return "covid";
  }

  @Override
  public String getAuthor() {
    return "HSGamer";
  }

  @Override
  public String getVersion() {
    return version;
  }

  @Override
  public Map<String, Object> getDefaults() {
    Map<String, Object> map = new HashMap<>();
    map.put("interval", 300);
    map.put("country", Arrays.asList("US", "VN"));
    return map;
  }

  @Override
  public void start() {
    int interval = getInt("interval", 200);
    statsMap.put("global", new GlobalStats(getPlaceholderAPI()));
    getStringList("country")
        .forEach(country -> statsMap.put(country, new CountryStats(getPlaceholderAPI(), country)));
    statsMap.forEach((name, stats) -> tasks
        .add(stats.runTaskTimerAsynchronously(getPlaceholderAPI(), 0, interval)));
  }

  @Override
  public void stop() {
    tasks.forEach(BukkitTask::cancel);
    tasks.clear();
    statsMap.clear();
  }

  @Override
  public String onRequest(OfflinePlayer player, String identifier) {
    String addr = identifier.substring(identifier.lastIndexOf('_') + 1);
    if (statsMap.containsKey(addr)) {
      Stats stats = statsMap.get(addr);
      switch (identifier.substring(0, identifier.lastIndexOf('_')).toLowerCase()) {
        case "cases":
          return String.valueOf(stats.getCases());
        case "recovered":
          return String.valueOf(stats.getRecovered());
        case "unresolved":
          return String.valueOf(stats.getUnresolved());
        case "deaths":
          return String.valueOf(stats.getDeaths());
        case "new_cases":
          return String.valueOf(stats.getNewCases());
        case "new_deaths":
          return String.valueOf(stats.getNewDeaths());
        case "active_cases":
          return String.valueOf(stats.getActiveCases());
        case "serious_cases":
          return String.valueOf(stats.getSeriousCases());
        default:
          break;
      }
    }
    return null;
  }
}
