package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.util.ConfigHandler;
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

    public String rushWorld = ConfigHandler.rushWorld;
    public boolean deathGamemode = ConfigHandler.deathGameMode;
    public boolean killEffect = ConfigHandler.killEffect;
    public Integer respawnDelay = ConfigHandler.respawnDelay;
    public String respawnTitle = ConfigHandler.respawnTitle;
    public String respawnSubTitle = ConfigHandler.respawnSubTitle;
    public String respawnSuccTitle = ConfigHandler.respawnSuccTitle;
    public String respawnSuccSubTitle = ConfigHandler.respawnSuccSubTitle;

    @EventHandler
    public void on(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        String world = p.getWorld().getName();

        if (deathGamemode && e.getEntity().getWorld().getName().contains(rushWorld)) {
            Location deathLocation = p.getLocation();
            if (killEffect) {
                p.getWorld().strikeLightning(deathLocation);
            }
            GameManager gm = BedwarsRel.getInstance().getGameManager();
            Game game = gm.getGameOfPlayer(p);
            Location spawnLocation = gm.getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();
            new BukkitRunnable() {
                int x = respawnDelay;

                @Override
                public void run() {
                    if (x != 0) {
                        x--;
                        String i = Integer.toString(x);
                        String respawnTitleReal = respawnTitle.replace("{timeleft}", i);
                        String respawnSubtitleReal = respawnSubTitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawnTitleReal), t(respawnSubtitleReal));
                    }
                    if (x == 0) {
                        String i = Integer.toString(x);
                        String respawnSuccTitleReal = respawnSuccTitle.replace("{timeleft}", i);
                        String respawnSuccSubtitleReal = respawnSuccSubTitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawnSuccTitleReal), t(respawnSuccSubtitleReal));
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(spawnLocation);
                        cancel();
                    }
                    if (!world.contains(rushWorld) || game.getTimeLeft() == game.getLength()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 20L, 20L);
        }
    }
}
