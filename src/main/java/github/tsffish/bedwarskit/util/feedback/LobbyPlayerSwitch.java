package github.tsffish.bedwarskit.util.feedback;

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
        boolean isInLobbyWorld = world.contains(lobbyWorld);
        if (switchworldFall && isInLobbyWorld) {
            p.teleport(loc);
            p.setFallDistance(512.0F);
        }
        if (isInLobbyWorld) {
            p.setGameMode(GameMode.SURVIVAL);
        }
    }
}
