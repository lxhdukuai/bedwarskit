package github.tsffish.bedwarskit.feedback;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * @author tsffish
 */
public class LobbyPlayerSwitch implements Listener {
    public boolean switchworldFall = MainConfigHandler.switchworldFall;
    public String lobbyWorld = MainConfigHandler.lobbyWorld;
    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        Location loc = p.getLocation();
        loc.setY(loc.getY() + 1);
        if (switchworldFall && world.contains(lobbyWorld)) {
            p.teleport(loc);
            p.setFallDistance(512.0F);
        }
        if (p.getWorld().getName().contains(lobbyWorld)) {
            p.setGameMode(GameMode.SURVIVAL);
        }
    }
}