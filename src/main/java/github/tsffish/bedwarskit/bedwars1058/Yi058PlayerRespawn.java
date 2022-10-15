package github.tsffish.bedwarskit.bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import github.tsffish.bedwarskit.util.ConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Administrator
 */
public class Yi058PlayerRespawn implements Listener {
    public boolean deathGamemode = ConfigHandler.deathGameMode;
    public String lobbyWorld = ConfigHandler.lobbyWorld;

    @EventHandler
    public void on(PlayerReSpawnEvent e) {
        if (deathGamemode && !e.getPlayer().getWorld().getName().contains(lobbyWorld)) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
