package org.blockface.chunkyhomes.listeners;

import com.dumptruckman.chunky.ChunkyManager;
import com.dumptruckman.chunky.object.ChunkyPlayer;
import org.blockface.chunkyhomes.persistance.Persistance;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerEvents extends PlayerListener {
    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        ChunkyPlayer chunkyPlayer = ChunkyManager.getChunkyPlayer(event.getPlayer().getName());
        event.setRespawnLocation(Persistance.getHome(chunkyPlayer));
    }
}
