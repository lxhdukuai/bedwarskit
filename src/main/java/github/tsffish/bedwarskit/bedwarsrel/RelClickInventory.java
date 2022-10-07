package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Administrator
 */
public class RelClickInventory implements Listener {
    private final Main plugin;

    public RelClickInventory(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(InventoryClickEvent e) {
        Material currentItemType = e.getCurrentItem().getType();
        if (plugin.antiDrop && e.getWhoClicked().getWorld().getName().contains(plugin.rushWorld)) {
            if (currentItemType == Material.LEATHER_HELMET || currentItemType == Material.LEATHER_CHESTPLATE ||
                    currentItemType == Material.LEATHER_LEGGINGS || currentItemType == Material.LEATHER_BOOTS ||
                    currentItemType == Material.CHAINMAIL_LEGGINGS || currentItemType == Material.CHAINMAIL_BOOTS ||
                    currentItemType == Material.IRON_LEGGINGS || currentItemType == Material.IRON_BOOTS ||
                    currentItemType == Material.DIAMOND_LEGGINGS || currentItemType == Material.DIAMOND_BOOTS) {
                e.setCancelled(true);
            }
        }
    }

}
