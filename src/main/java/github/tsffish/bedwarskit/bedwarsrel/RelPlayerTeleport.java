package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.util.ConfigHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author Administrator
 */
public class RelPlayerTeleport implements Listener {
    public boolean noPearlDamage = ConfigHandler.noPearlDamage;
    public String rushWorld = ConfigHandler.rushWorld;

    @EventHandler
    public void on(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;
        if (noPearlDamage && world.contains(rushWorld) && e.getCause() == enderpearl) {
            e.setCancelled(true);
            p.teleport(e.getTo());
            p.setFallDistance(0.0f);
        }
    }
}
