package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RelPlayerMove implements Listener {
    private final Main plugin;
    public RelPlayerMove(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String playerName = p.getPlayer().getName();
        Inventory inv = p.getInventory();
        new BukkitRunnable() {
            public void run() {

                if (p.getWorld().getName().contains(plugin.rushWorld) && plugin.nohunger && p.getFoodLevel() != 20) {
                    p.setFoodLevel(20);
                }
                if (p.getWorld().getName().contains(plugin.rushWorld) && BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getTimeLeft() < BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getLength()) {
                    if (!inv.contains(Material.WOOD_SWORD)) {
                        if (!inv.contains(Material.STONE_SWORD) || !inv.contains(Material.IRON_SWORD) || !inv.contains(Material.IRON_SWORD)) {
                            if (!inv.contains(Material.WOOD_SWORD)) {
                                inv.addItem(new ItemStack(Material.WOOD_SWORD));
                            }
                        }
                    }
                    if (inv.contains(Material.STONE_SWORD) || inv.contains(Material.IRON_SWORD) || inv.contains(Material.DIAMOND_SWORD)) {
                        inv.removeItem(new ItemStack(Material.WOOD_SWORD));
                    }
                    if (inv.contains(Material.CHAINMAIL_CHESTPLATE)) {
                        if (plugin.armorChain.contains(playerName) && plugin.armorIron.contains(playerName) && plugin.armorDiamond.contains(playerName)) {return;}else {
                            plugin.armorChain.add(playerName);
                            inv.remove(Material.CHAINMAIL_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        }
                    }
                    if (inv.contains(Material.IRON_CHESTPLATE)) {
                        if (plugin.armorIron.contains(playerName) && plugin.armorDiamond.contains(playerName)) {return;}else {
                            plugin.armorIron.add(playerName);
                            inv.remove(Material.IRON_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                        }
                    }
                    if (inv.contains(Material.DIAMOND_CHESTPLATE)) {
                        if (plugin.armorDiamond.contains(playerName)) {return;} else {
                            plugin.armorDiamond.add(playerName);
                            inv.remove(Material.DIAMOND_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                        }
                    }
                }
                cancel();
            }
            //End
        }.runTaskLater(plugin, 5L);
    }
}
