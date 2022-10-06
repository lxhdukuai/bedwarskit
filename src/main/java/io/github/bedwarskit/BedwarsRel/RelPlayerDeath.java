package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.bedwarskit.Util.ColorString.t;

public class RelPlayerDeath implements Listener {
    private final Main plugin;
    public RelPlayerDeath(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        if (plugin.death_gamemode && e.getEntity().getWorld().getName().contains(plugin.rushWorld)) {
            Location deathLocation = p.getLocation();
            if (plugin.kill_effect){
                p.getWorld().strikeLightning(deathLocation);
            }
            Location spawnLocation = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();
            new BukkitRunnable() {
                int x = plugin.respawn_delay;

                @Override
                public void run() {

                    GameManager gm = BedwarsRel.getInstance().getGameManager();

                    if (x != 0) {
                        x--;
                        String i = Integer.toString(x);
                        String respawn_title_real = plugin.respawn_title.replace("{timeleft}", i);
                        String respawn_subtitle_real = plugin.respawn_subtitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawn_title_real), t(respawn_subtitle_real));
                    }
                    if (x == 0) {
                        String i = Integer.toString(x);
                        String respawn_succ_title_real = plugin.respawn_succ_title.replace("{timeleft}", i);
                        String respawn_succ_subtitle_real = plugin.respawn_succ_subtitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawn_succ_title_real), t(respawn_succ_subtitle_real));
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(spawnLocation);
                        cancel();
                    }
                    if (!p.getWorld().getName().contains(plugin.rushWorld) || gm.getGameOfPlayer(p).getTimeLeft() == gm.getGameOfPlayer(p).getLength()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 20L, 20L);
        }
    }
}
