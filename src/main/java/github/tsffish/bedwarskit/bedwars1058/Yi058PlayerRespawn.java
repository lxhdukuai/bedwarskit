package github.tsffish.bedwarskit.bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import github.tsffish.bedwarskit.Main;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Administrator
 */
public class Yi058PlayerRespawn implements Listener {
    private final Main plugin;

    public Yi058PlayerRespawn(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerReSpawnEvent e) {
        if (plugin.deathGamemode && e.getPlayer().getWorld().getName().contains(plugin.rushWorld)) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
