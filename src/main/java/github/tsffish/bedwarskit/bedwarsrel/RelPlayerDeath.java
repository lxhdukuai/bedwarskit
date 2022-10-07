package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.util.ColorString.t;

/**
 * @author Administrator
 */
public class RelPlayerDeath implements Listener {
    private final Main plugin;

    public RelPlayerDeath(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        String world = p.getWorld().getName();

        if (plugin.deathGamemode && e.getEntity().getWorld().getName().contains(plugin.rushWorld)) {
            Location deathLocation = p.getLocation();
            if (plugin.killEffect) {
                p.getWorld().strikeLightning(deathLocation);
            }
            GameManager gm = BedwarsRel.getInstance().getGameManager();
            Game game = gm.getGameOfPlayer(p);
            Location spawnLocation = gm.getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();
            new BukkitRunnable() {
                int x = plugin.respawnDelay;

                @Override
                public void run() {
                    if (x != 0) {
                        x--;
                        String i = Integer.toString(x);
                        String respawnTitleReal = plugin.respawnTitle.replace("{timeleft}", i);
                        String respawnSubtitleReal = plugin.respawnSubtitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawnTitleReal), t(respawnSubtitleReal));
                    }
                    if (x == 0) {
                        String i = Integer.toString(x);
                        String respawnSuccTitleReal = plugin.respawnSuccTitle.replace("{timeleft}", i);
                        String respawnSuccSubtitleReal = plugin.respawnSuccSubtitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawnSuccTitleReal), t(respawnSuccSubtitleReal));
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(spawnLocation);
                        cancel();
                    }
                    if (!world.contains(plugin.rushWorld) || game.getTimeLeft() == game.getLength()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 20L, 20L);
        }
    }
}
