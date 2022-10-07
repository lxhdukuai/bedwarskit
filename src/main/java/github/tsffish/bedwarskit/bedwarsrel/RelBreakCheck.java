package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Administrator
 */
public class RelBreakCheck implements Listener {
    private final Main plugin;

    public RelBreakCheck(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        Location tele = p.getLocation().add(0.0, plugin.tpDis, 0.0);
        loc.setY(loc.getY() - 0.07);
        Material block = loc.getWorld().getBlockAt(loc).getType();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (plugin.breakBedCheck) {
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
