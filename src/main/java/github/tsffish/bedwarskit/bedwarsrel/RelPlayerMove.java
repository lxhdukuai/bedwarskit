package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelArmorList;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author tsffish
 */
public class RelPlayerMove implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);
    public boolean noHunger = MainConfigHandler.noHunger;
    public Integer maxFoodLevel = MainConfigHandler.maxFoodLevel;
    public String rushWorld = MainConfigHandler.rushWorld;

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String playerName = p.getPlayer().getName();
        String world = p.getWorld().getName();
        Inventory inv = p.getInventory();
        GameManager gm = BedwarsRel.getInstance().getGameManager();

        new BukkitRunnable() {
            @Override
            public void run() {
                Game game = gm.getGameOfPlayer(p);
                // 设置饱食度
                if (noHunger && p.getWorld().getName().contains(rushWorld) && p.getFoodLevel() != maxFoodLevel) {
                    p.setFoodLevel(maxFoodLevel);
                }

                // 设置装备
                if (world.contains(rushWorld) && game.getTimeLeft() < game.getLength()) {
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
                        if (RelArmorList.armorChain.contains(playerName) || RelArmorList.armorIron.contains(playerName) || RelArmorList.armorDiamond.contains(playerName)) {
                            return;
                        } else {
                            RelArmorList.armorChain.add(playerName);
                            inv.remove(Material.CHAINMAIL_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        }
                    }
                    if (inv.contains(Material.IRON_CHESTPLATE)) {
                        if (RelArmorList.armorIron.contains(playerName) || RelArmorList.armorDiamond.contains(playerName)) {
                            return;
                        } else {
                            RelArmorList.armorIron.add(playerName);
                            inv.remove(Material.IRON_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                        }
                    }
                    if (inv.contains(Material.DIAMOND_CHESTPLATE)) {
                        if (RelArmorList.armorDiamond.contains(playerName)) {
                            return;
                        } else {
                            RelArmorList.armorDiamond.add(playerName);
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
