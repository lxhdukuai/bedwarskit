package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.util.ConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

/**
 * @author Administrator
 */
public class RelKillEffect implements Listener {
    public boolean killEffect = ConfigHandler.killEffect;

    @EventHandler
    public void on(BlockIgniteEvent e) {
        if(e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
            e.setCancelled(true);
        }
    }
}
