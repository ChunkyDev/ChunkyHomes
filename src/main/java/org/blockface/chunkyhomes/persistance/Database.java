package org.blockface.chunkyhomes.persistance;

import com.dumptruckman.chunky.object.ChunkyPlayer;
import org.bukkit.Location;


public interface Database {

    public void saveHome(ChunkyPlayer player, Location location);

    public Location getHome(ChunkyPlayer player);

    public boolean isHomeSet(ChunkyPlayer player);

    public void save();

}
