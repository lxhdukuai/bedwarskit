package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author Administrator
 */
public class RelPlayerTeleport implements Listener {
    private final Main plugin;

    public RelPlayerTeleport(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;
        if (plugin.noPearlDamage && world.contains(plugin.rushWorld) && e.getCause() == enderpearl) {
            e.setCancelled(true);
            p.teleport(e.getTo());
            p.setFallDistance(0.0f);
        }
    }
}
