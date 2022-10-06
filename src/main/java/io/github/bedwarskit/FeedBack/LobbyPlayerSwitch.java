package io.github.bedwarskit.FeedBack;

import io.github.bedwarskit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class LobbyPlayerSwitch implements Listener {
    private final Main plugin;
    public LobbyPlayerSwitch(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        if(plugin.switchworld_fall && e.getPlayer().getWorld().getName().contains(plugin.lobbyworld)){
            p.setFallDistance(512.0f);
        }
    }
}
