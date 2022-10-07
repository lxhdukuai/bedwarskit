package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

/**
 * @author Administrator
 */
public class RelKillEffect implements Listener {
    private final Main plugin;

    public RelKillEffect(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(BlockIgniteEvent e) {
        if (plugin.killEffect && e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
            e.setCancelled(true);
        }
    }
}
