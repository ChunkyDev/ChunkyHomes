package org.blockface.chunkyhomes.persistance;

import com.dumptruckman.chunky.object.ChunkyChunk;
import com.dumptruckman.chunky.object.ChunkyObject;
import com.dumptruckman.chunky.object.ChunkyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;


public class YAML implements Database{

    Configuration data;

    public YAML(Plugin plugin) {
        File file = new File(plugin.getDataFolder(),"data.yml");
        if(!file.exists())
            try {file.createNewFile();}
            catch (IOException e) {return;}
        data = new Configuration(file);
        data.load();
    }


    public void saveHome(ChunkyPlayer player, Location location) {
        String name = player.getName();
        data.setProperty(name, location.getWorld().getName() + ";" + location.getBlockX()+ ";" + location.getBlockY()+ ";" + location.getBlockZ());
        data.save();
    }

    public Location getHome(ChunkyPlayer player) {
        String name = player.getName();
        if(!isHomeSet(player)) return guessHome(player);
        String line = data.getString(name);
        String[] splits = line.split(";");
        String world = splits[0];
        int x = Integer.parseInt(splits[1]);
        int y = Integer.parseInt(splits[2]);
        int z = Integer.parseInt(splits[3]);
        return new Location(Bukkit.getServer().getWorld(world), x,y,z);
    }

    private Location guessHome(ChunkyPlayer player) {
        HashSet<ChunkyObject> chunks = player.getOwnables().get(ChunkyChunk.class.getName());
        if(chunks.size() ==0) return null;
        ChunkyChunk chunk = (ChunkyChunk)chunks.iterator().next();
        World world = Bukkit.getServer().getWorld(chunk.getCoord().getWorld());
        Block block = world.getChunkAt(chunk.getCoord().getX(),chunk.getCoord().getZ()).getBlock(8,8,8);
        return world.getHighestBlockAt(block.getLocation()).getLocation();

    }

    public boolean isHomeSet(ChunkyPlayer player) {
        return !data.getString(player.getName(),"notSet").equals("notSet");
    }

    public void save() {
        data.save();
    }
}
