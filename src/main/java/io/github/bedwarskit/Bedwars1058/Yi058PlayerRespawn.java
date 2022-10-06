package io.github.bedwarskit.Bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import io.github.bedwarskit.Main;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Yi058PlayerRespawn implements Listener {
    private final Main plugin;
    public Yi058PlayerRespawn(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerReSpawnEvent e) {
        if (plugin.death_gamemode && e.getPlayer().getWorld().getName().contains(plugin.rushWorld)){
         e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
