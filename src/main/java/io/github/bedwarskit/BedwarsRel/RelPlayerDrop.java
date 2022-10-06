package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;

public class RelPlayerDrop implements Listener {
    private final Main plugin;
    public RelPlayerDrop(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();

        if (plugin.anti_drop && p.getWorld().getName().contains(plugin.rushWorld)) {
            if (e.getItemDrop().getItemStack().getType() == Material.WOOD_SWORD) {
                if (!inv.contains(Material.STONE_SWORD) && !inv.contains(Material.IRON_SWORD) && !inv.contains(Material.DIAMOND_SWORD)) {
                    e.setCancelled(true);
                }
            }
            if (e.getItemDrop().getItemStack().getType() == Material.STONE_SWORD) {
                if (inv.contains(Material.IRON_SWORD) && inv.contains(Material.DIAMOND_SWORD)) {
                    e.setCancelled(true);
                }
            }

            if (e.getItemDrop().getItemStack().getType() == Material.IRON_SWORD) {
                if (inv.contains(Material.DIAMOND_SWORD)) {
                    e.setCancelled(true);
                }
            }
            if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD) {
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.LEATHER_HELMET) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.LEATHER_CHESTPLATE) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.LEATHER_LEGGINGS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.LEATHER_BOOTS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.CHAINMAIL_CHESTPLATE) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.CHAINMAIL_LEGGINGS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.CHAINMAIL_BOOTS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.IRON_CHESTPLATE) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.IRON_LEGGINGS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.IRON_BOOTS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_CHESTPLATE) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_LEGGINGS) {
                e.setCancelled(true);
            } else if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_BOOTS) {
                e.setCancelled(true);
            }
        }
    }
}
