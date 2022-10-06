package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RelClickInventory implements Listener {
    private final Main plugin;

    public RelClickInventory(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(InventoryClickEvent e) {
        if (plugin.anti_drop && e.getWhoClicked().getWorld().getName().contains(plugin.rushWorld)) {
            if (e.getCurrentItem().getType() == Material.LEATHER_HELMET || e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE ||
                    e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS || e.getCurrentItem().getType() == Material.LEATHER_BOOTS ||
                    e.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS || e.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS ||
                    e.getCurrentItem().getType() == Material.IRON_LEGGINGS || e.getCurrentItem().getType() == Material.IRON_LEGGINGS ||
                    e.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS || e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
                e.setCancelled(true);
            }
        }
    }

}
