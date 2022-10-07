package github.tsffish.bedwarskit.feedback;

import github.tsffish.bedwarskit.Main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * @author Administrator
 */
public class LobbyPlayerSwitch implements Listener {
    private final Main plugin;

    public LobbyPlayerSwitch(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        Location loc = p.getLocation();
        loc.setY(loc.getY() + 1);
        if (plugin.switchworldFall && world.contains(plugin.lobbyworld)) {
            p.teleport(loc);
            p.setFallDistance(512.0f);
        }
        if (p.getWorld().getName().contains(plugin.lobbyworld)) {
            p.setGameMode(GameMode.SURVIVAL);
        }
    }
}
