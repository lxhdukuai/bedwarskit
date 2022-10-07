package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author Administrator
 */
public class RelPlayerDrop implements Listener {
    private final Main plugin;

    public RelPlayerDrop(Main plugin) {
        this.plugin = plugin;
    }

    Material woodSword = Material.WOOD_SWORD;
    Material stoneSword = Material.STONE_SWORD;
    Material ironSword = Material.IRON_SWORD;
    Material diamondSword = Material.DIAMOND_SWORD;

    @EventHandler
    public void on(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();
        Material dropItemType = e.getItemDrop().getItemStack().getType();
        if (plugin.antiDrop && p.getWorld().getName().contains(plugin.rushWorld)) {
            if (dropItemType == woodSword) {
                if (!inv.contains(stoneSword) && !inv.contains(ironSword) && !inv.contains(diamondSword)) {
                    e.setCancelled(true);
                }
                if (dropItemType == Material.LEATHER_HELMET) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.LEATHER_CHESTPLATE) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.LEATHER_LEGGINGS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.LEATHER_BOOTS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.CHAINMAIL_CHESTPLATE) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.CHAINMAIL_LEGGINGS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.CHAINMAIL_BOOTS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.IRON_CHESTPLATE) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.IRON_LEGGINGS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.IRON_BOOTS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.DIAMOND_CHESTPLATE) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.DIAMOND_LEGGINGS) {
                    e.setCancelled(true);
                } else if (dropItemType == Material.DIAMOND_BOOTS) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
