package me.maladik.easydate.Placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.maladik.easydate.EasyDate;
import me.maladik.easydate.SettingsFiles.LanguageFile;
import me.maladik.easydate.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DateAPI extends PlaceholderExpansion {

    private final EasyDate myPlugin;

    public DateAPI(EasyDate plugin) {
        myPlugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "easydate";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Maladik_40";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {

        LanguageFile langFile = myPlugin.getLangFile();
        FileConfiguration langYaml = langFile.getConfiguration();

        // NAMES

        List<String> itMonths = new ArrayList<>();

        itMonths.addAll(langYaml.getStringList("it_it.months-names"));

        List<String> itDays = new ArrayList<>();

        itDays.addAll(langYaml.getStringList("it_it.days-names"));

        List<String> enMonths = new ArrayList<>();

        enMonths.addAll(langYaml.getStringList("en_us.months-names"));

        List<String> enDays = new ArrayList<>();

        enDays.addAll(langYaml.getStringList("en_us.days-names"));

        if (identifier.equals("eu_formatted_date")) {
            return Utils.DAY + "/" + Utils.MONTH + "/" + Utils.YEAR;
        }

        if (identifier.equals("us_formatted_date")) {
            return  Utils.MONTH + "/" + Utils.DAY + "/" + Utils.YEAR;
        }

        if (identifier.equals("it_eu_show_date")) {

            int dayName = Utils.CURRENT_DAY_NAME;
            return itDays.get(dayName) + " " + Utils.DAY + " " + itMonths.get(Utils.MONTH - 1) + " " + Utils.YEAR;
        }

        if (identifier.equals("en_us_show_date")) {

            int dayName = Utils.CURRENT_DAY_NAME;
            return enDays.get(dayName) + " " + Utils.DAY + " " + enMonths.get(Utils.MONTH - 1) + " " + Utils.YEAR;

        }

        if (identifier.equals("day_number")) {
            return "" + Utils.DAY;
        }

        if (identifier.equals("day_us_name")) {
            return itDays.get(Utils.CURRENT_DAY_NAME);
        }

        if (identifier.equals("day_it_name")) {
            return "" + enDays.get(Utils.CURRENT_DAY_NAME);
        }

        if (identifier.equals("month_number")) {
            return "" + Utils.MONTH;
        }

        if (identifier.equals("month_us_name")) {
            return "" + enMonths.get(Utils.MONTH);
        }

        if (identifier.equals("month_it_name")) {
            return "" + itMonths.get(Utils.MONTH);
        }

        if (identifier.equals("year")) {
            return "" + Utils.YEAR;
        }

        return null;
    }

}
