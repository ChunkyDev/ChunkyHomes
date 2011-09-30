package org.blockface.chunkyhomes.commands;

import com.dumptruckman.chunky.ChunkyManager;
import com.dumptruckman.chunky.locale.Language;
import com.dumptruckman.chunky.module.ChunkyCommand;
import com.dumptruckman.chunky.module.ChunkyCommandExecutor;
import com.dumptruckman.chunky.object.ChunkyChunk;
import com.dumptruckman.chunky.object.ChunkyPlayer;
import com.dumptruckman.chunky.permission.ChunkyPermissions;
import org.blockface.chunkyhomes.persistance.Persistance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements ChunkyCommandExecutor{

    public void onCommand(CommandSender sender, ChunkyCommand chunkyCommand, String s, String[] strings) {
        if(!(sender instanceof Player)) {
            Language.IN_GAME_ONLY.bad(sender);
            return;}

        Player player = (Player)sender;
        ChunkyPlayer chunkyPlayer = ChunkyManager.getChunkyPlayer(player.getName());
        ChunkyChunk currentChunk = ChunkyManager.getChunk(player.getLocation());
        if(!currentChunk.isOwned() || !currentChunk.isOwnedBy(chunkyPlayer)) {
            Language.CHUNK_NOT_OWNED.bad(chunkyPlayer);
            return;}

        Persistance.saveHome(chunkyPlayer, player.getLocation());
        Language.sendMessage(chunkyPlayer,"Home set!");




    }

}

