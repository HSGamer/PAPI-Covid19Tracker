package me.hsgamer.covid19trackerexpansion.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Stats extends BukkitRunnable {

    protected String cases = "";
    protected String recovered = "";
    protected String deaths = "";
    protected String activeCases = "";
    protected String seriousCases = "";
    protected String todayCases = "";
    protected String todayDeaths = "";
    protected String todayRecovered = "";
    protected JavaPlugin plugin;

    public Stats(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String getCases() {
        return cases;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public String getSeriousCases() {
        return seriousCases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }
}
