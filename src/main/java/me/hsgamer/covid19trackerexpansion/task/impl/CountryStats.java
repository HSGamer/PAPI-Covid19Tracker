package me.hsgamer.covid19trackerexpansion.task.impl;

import me.hsgamer.covid19trackerexpansion.Utils;
import me.hsgamer.covid19trackerexpansion.task.Stats;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

public class CountryStats extends Stats {

    private final String url;

    public CountryStats(JavaPlugin plugin, String country) {
        super(plugin);
        url = "https://disease.sh/v3/covid-19/countries/" + country + "?strict=true";
    }

    @Override
    public void run() {
        JSONObject jsonObject = Utils.getJSONObjectFromURL(url);
        if (jsonObject == null || Utils.isInvalid(jsonObject)) {
            return;
        }

        cases = String.valueOf(jsonObject.get("cases"));
        recovered = String.valueOf(jsonObject.get("recovered"));
        deaths = String.valueOf(jsonObject.get("deaths"));
        activeCases = String.valueOf(jsonObject.get("active"));
        seriousCases = String.valueOf(jsonObject.get("critical"));
        todayCases = String.valueOf(jsonObject.get("todayCases"));
        todayDeaths = String.valueOf(jsonObject.get("todayDeaths"));
        todayRecovered = String.valueOf(jsonObject.get("todayRecovered"));
    }
}
