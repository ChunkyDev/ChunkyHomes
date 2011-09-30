package org.blockface.chunkyhomes.persistance;

import com.dumptruckman.chunky.object.ChunkyPlayer;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class Persistance {

    private static Database database;

    public static void load(Plugin plugin) {
        database = new YAML(plugin);
    }


    public static void saveHome(ChunkyPlayer player, Location location) {
        database.saveHome(player,location);
    }

    public static Location getHome(ChunkyPlayer player) {
        return database.getHome(player);
    }

    public static boolean isHomeSet(ChunkyPlayer player) {
        return database.isHomeSet(player);
    }

    public static void save() {
        database.save();
    }
}
