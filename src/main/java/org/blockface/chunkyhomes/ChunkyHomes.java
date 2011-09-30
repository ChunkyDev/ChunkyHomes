package org.blockface.chunkyhomes;

import com.dumptruckman.chunky.Chunky;
import com.dumptruckman.chunky.exceptions.ChunkyUnregisteredException;
import com.dumptruckman.chunky.module.ChunkyCommand;
import org.blockface.chunkyhomes.commands.Home;
import org.blockface.chunkyhomes.commands.SetHome;
import org.blockface.chunkyhomes.listeners.PlayerEvents;
import org.blockface.chunkyhomes.persistance.Persistance;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class ChunkyHomes extends JavaPlugin {
    public void onDisable() {
        Persistance.save();
        System.out.println(this + " is now disabled!");
    }

    public void onEnable() {
        Config.load(this);
        Persistance.load(this);
        Chunky.getModuleManager().registerModule(this);
        try {
            ChunkyCommand root = Chunky.getModuleManager().getCommandByName("chunky");
            Chunky.getModuleManager()
                    .registerCommand(
                            new ChunkyCommand(
                                "sethome",Arrays.asList("sh"),"Set your home to teleport to with /home",Arrays.asList("/c sethome or /c sh"),new SetHome(),root));
            Chunky.getModuleManager()
                    .registerCommand(
                            new ChunkyCommand(
                                "home",Arrays.asList("h"),"Teleport to your home",Arrays.asList("/c home or /c h"),new Home(),root));
        } catch (Exception e) {}

        if(Config.isRespawnHome())
            this.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_RESPAWN, new PlayerEvents(), Event.Priority.Normal, this);

        System.out.println(this + " is now enabled!");
    }


}
