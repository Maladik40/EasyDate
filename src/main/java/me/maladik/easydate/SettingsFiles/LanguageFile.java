package me.maladik.easydate.SettingsFiles;

import me.maladik.easydate.EasyDate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageFile {

    private File file;
    private FileConfiguration configuration;

    private EasyDate myPlugin;

    public LanguageFile(EasyDate plugin) {
        myPlugin = plugin;
    }

    public void load() {
        file = new File(myPlugin.getDataFolder(), "lang.yml");

        if (!file.exists()) {
            myPlugin.saveResource("lang.yml", false);
        }

        configuration = YamlConfiguration.loadConfiguration(file);

    }

    public void save() {
        try {
            configuration.save(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        configuration.set(path, value);

        save();
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }


}
