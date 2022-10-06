package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RelBreakCheck implements Listener {
    private final Main plugin;
    public RelBreakCheck(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(BlockBreakEvent e) {
        // Velocity (Failed)
        // Vector te = p.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().setY(0.3).multiply(2);
        // p.setvelocity(te);
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        Location tele = p.getLocation().add(0.0, plugin.tp_dis, 0.0);
        loc.setY(loc.getY() - 0.07);
        Material block = loc.getWorld().getBlockAt(loc).getType();
        new BukkitRunnable() {
            public void run() {
                if (plugin.break_bed_check) {
                    Material breakblock = e.getBlock().getType();
                    if (block == Material.BED_BLOCK && breakblock == Material.BED_BLOCK) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.teleport(tele);
                        p.setFallDistance(0.0f);
                    }
                }
            }
            //End
        }.runTaskLater(plugin, 1L);
    }
}
