package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class RelKillEffect implements Listener {
    private final Main plugin;
    public RelKillEffect(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(BlockIgniteEvent e){
        if (plugin.kill_effect && e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING){
            e.setCancelled(true);
        }
    }
}
