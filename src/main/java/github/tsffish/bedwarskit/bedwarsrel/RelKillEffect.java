package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

/**
 * @author tsffish
 * @see RelPlayerDeath
 */
public class RelKillEffect implements Listener {
    public boolean killEffect = MainConfigHandler.killEffect;
    @EventHandler
    public void on(BlockIgniteEvent e) {
        // 防止击杀特效闪电产生的起火
        // 击杀效果请看 RelPlayerDeath
        if(killEffect && e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
            e.setCancelled(true);
        }
    }
}
