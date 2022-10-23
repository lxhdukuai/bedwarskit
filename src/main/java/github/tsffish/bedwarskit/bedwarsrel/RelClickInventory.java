package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static github.tsffish.bedwarskit.util.RelNoDropList.noDropList;

/**
 * @author tsffish
 * @see RelPlayerDrop
 */
public class RelClickInventory implements Listener {
    public boolean antiDrop = MainConfigHandler.antiDrop;
    public String rushWorld = MainConfigHandler.rushWorld;
    @EventHandler
    public void on(InventoryClickEvent e) {
        Material currentItemType = e.getCurrentItem().getType();
        if (antiDrop && e.getWhoClicked().getWorld().getName().contains(rushWorld)) {
            if (noDropList.contains(currentItemType)) {
                e.setCancelled(true);
            }
        }
    }

}
