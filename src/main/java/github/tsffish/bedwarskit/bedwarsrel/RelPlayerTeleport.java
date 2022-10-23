package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author tsffish
 */
public class RelPlayerTeleport implements Listener {
    public boolean noPearlDamage = MainConfigHandler.noPearlDamage;
    public String rushWorld = MainConfigHandler.rushWorld;
    @EventHandler
    public void on(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        // 禁用末影珍珠伤害
        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;
        if (noPearlDamage && world.contains(rushWorld) && e.getCause() == enderpearl) {
            e.setCancelled(true);
            p.teleport(e.getTo());
            // 如果不设置摔落距离会导致出现摔落伤害
            p.setFallDistance(0.0f);
        }
    }
}
