package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class RelPlayerTeleport implements Listener {
    private final Main plugin;
    public RelPlayerTeleport(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        if(plugin.no_pearl_damage && p.getWorld().getName().contains(plugin.rushWorld) && e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            e.setCancelled(true);
            p.teleport(e.getTo());
            p.setFallDistance(0.0f);
        }
    }
}
