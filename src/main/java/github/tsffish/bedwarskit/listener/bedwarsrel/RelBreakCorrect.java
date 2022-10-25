package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author tsffish
 * @see RelBreakBed
 */
public class RelBreakCorrect implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);
    public boolean breakBedCheck = MainConfigHandler.breakBedCheck;
    public double tpDis = MainConfigHandler.tpDis;
    @EventHandler
    public void on(BlockBreakEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (breakBedCheck) {
                    Player p = e.getPlayer();
                    Location loc = p.getLocation();
                    Location tele = p.getLocation().add(0.0, tpDis, 0.0);
                    loc.setY(loc.getY() - 0.07);
                    Material block = loc.getWorld().getBlockAt(loc).getType();
                    GameManager gm = BedwarsRel.getInstance().getGameManager();
                    Game game = gm.getGameOfPlayer(p);
                    Block playerTeamBlock = game.getPlayerTeam(p).getTargetHeadBlock().getBlock();
                    Material breakblock = e.getBlock().getType();
                    if (block == Material.BED_BLOCK && breakblock == Material.BED_BLOCK && playerTeamBlock.getType() != Material.AIR) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.teleport(tele);
                        p.setFallDistance(0.0f);
                    }
                }
            }
            //End
        }.runTaskLater(plugin, 1L);
// 击退模式 未测试成功 无效
//CK!Velocity
//CK!Vector te = p.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().setY(0.3).multiply(2);
//CK!p.setvelocity(te);
    }
}
