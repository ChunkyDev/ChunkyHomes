package org.blockface.chunkyhomes;

import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

public class Config {
    private static Configuration configuration;

    public static void load(Plugin plugin) {
        plugin.getDataFolder().mkdir();
        configuration = plugin.getConfiguration();
        loadConfig();

    }

    private static void loadConfig() {
        configuration.load();
        isRespawnHome();

        configuration.save();
    }

    public static boolean isRespawnHome() {
        return configuration.getBoolean("settings.RespawnAtHome",true);
    }

}
