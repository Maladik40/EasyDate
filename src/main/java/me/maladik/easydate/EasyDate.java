package me.maladik.easydate;

import me.maladik.easydate.Commands.MainCommand;
import me.maladik.easydate.Placeholders.DateAPI;
import me.maladik.easydate.SettingsFiles.LanguageFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class EasyDate extends JavaPlugin {

    private static EasyDate instance;

    public static FileConfiguration config;

    public static LanguageFile langFile;

    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            saveDefaultConfig();
            langFile = new LanguageFile(this);
            langFile.load();
            new DateAPI(this).register();

            instance = this;
            config = this.getConfig();

            loadLastDate();

            DayCycleRunnable dayCycleRunnable = new DayCycleRunnable(this);
            dayCycleRunnable.runTaskTimerAsynchronously(this, 1L, 5L);

            getCommand("easydate").setExecutor(new MainCommand());


        } else {
            getLogger().warning("Could no find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {

        saveLastDate();

        langFile.save();
        config = null;
        instance = null;
    }

    public static EasyDate getInstance() {
        return instance;
    }

    public LanguageFile getLangFile() {
        return langFile;
    }

    public void loadLastDate() {
        ConfigurationSection configS = getConfig().getConfigurationSection("last-date");
        for (String key : configS.getKeys(false)) {
            if (key.equalsIgnoreCase("day")) Utils.DAY = configS.getInt(key);
            if (key.equalsIgnoreCase("month")) Utils.MONTH = configS.getInt(key);
            if (key.equalsIgnoreCase("year")) Utils.YEAR = configS.getInt(key);
            if (key.equalsIgnoreCase("day-name")) Utils.CURRENT_DAY_NAME = configS.getInt(key);
        }
        saveConfig();
    }

    public void saveLastDate() {
        int day = Utils.DAY;
        int month = Utils.MONTH;
        int year = Utils.YEAR;
        int dayName = Utils.CURRENT_DAY_NAME;

        ConfigurationSection configS = getConfig().getConfigurationSection("last-date");

        configS.set("day", day);
        configS.set("month", month);
        configS.set("year", year);
        configS.set("day-name", dayName);
        saveConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        saveConfig();
    }
}
