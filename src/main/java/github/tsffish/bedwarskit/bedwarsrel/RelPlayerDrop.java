package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;

import static github.tsffish.bedwarskit.util.RelNoDropList.noDropList;

/**
 * @author tsffish
 * @see RelClickInventory
 */
public class RelPlayerDrop implements Listener {
    public boolean antiDrop = MainConfigHandler.antiDrop;
    public String rushWorld = MainConfigHandler.rushWorld;
    Material woodSword = Material.WOOD_SWORD;
    Material stoneSword = Material.STONE_SWORD;
    Material ironSword = Material.IRON_SWORD;
    Material diamondSword = Material.DIAMOND_SWORD;

    @EventHandler
    public void on(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();
        Material dropItemType = e.getItemDrop().getItemStack().getType();
        if (antiDrop && p.getWorld().getName().contains(rushWorld)) {
            if (dropItemType == woodSword) {
                if (!inv.contains(stoneSword) && !inv.contains(ironSword) && !inv.contains(diamondSword)) {
                    e.setCancelled(true);
                }
                if (noDropList.contains(dropItemType)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
