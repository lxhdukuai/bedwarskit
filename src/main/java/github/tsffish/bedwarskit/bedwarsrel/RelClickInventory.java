package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.util.ConfigHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Administrator
 */
public class RelClickInventory implements Listener {
    public boolean antiDrop = ConfigHandler.antiDrop;
    public String rushWorld = ConfigHandler.rushWorld;

    @EventHandler
    public void on(InventoryClickEvent e) {
        Material currentItemType = e.getCurrentItem().getType();
        if (antiDrop && e.getWhoClicked().getWorld().getName().contains(rushWorld)) {
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
