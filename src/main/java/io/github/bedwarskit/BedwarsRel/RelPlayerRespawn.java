package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class RelPlayerRespawn implements Listener {
    private final Main plugin;
    public RelPlayerRespawn(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().contains(plugin.rushWorld) && plugin.death_gamemode) {
            new BukkitRunnable() {
                public void run() {
                    p.setGameMode(GameMode.SPECTATOR);
                    Player player = e.getPlayer();

                    String playerteam = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player).getPlayerTeam(player).getColor().name();

                    ItemStack mao = new ItemStack(Material.LEATHER_HELMET);
                    ItemStack yi = new ItemStack(Material.LEATHER_CHESTPLATE);
                    ItemStack ku = new ItemStack(Material.LEATHER_LEGGINGS);
                    ItemStack xie = new ItemStack(Material.LEATHER_BOOTS);

                    LeatherArmorMeta mao_meta = (LeatherArmorMeta) mao.getItemMeta();
                    LeatherArmorMeta yi_meta = (LeatherArmorMeta) yi.getItemMeta();
                    LeatherArmorMeta ku_meta = (LeatherArmorMeta) ku.getItemMeta();
                    LeatherArmorMeta xie_meta = (LeatherArmorMeta) xie.getItemMeta();
                    switch (playerteam) {
                        case "RED":
                            mao_meta.setColor(Color.fromRGB(255, 0, 0));
                            yi_meta.setColor(Color.fromRGB(255, 0, 0));
                            ku_meta.setColor(Color.fromRGB(255, 0, 0));
                            xie_meta.setColor(Color.fromRGB(255, 0, 0));
                            break;
                        case "BLUE":
                            mao_meta.setColor(Color.fromRGB(0, 0, 255));
                            yi_meta.setColor(Color.fromRGB(0, 0, 255));
                            ku_meta.setColor(Color.fromRGB(0, 0, 255));
                            xie_meta.setColor(Color.fromRGB(0, 0, 255));
                            break;
                        case "GREEN":
                            mao_meta.setColor(Color.fromRGB(0, 255, 0));
                            yi_meta.setColor(Color.fromRGB(0, 255, 0));
                            ku_meta.setColor(Color.fromRGB(0, 255, 0));
                            xie_meta.setColor(Color.fromRGB(0, 255, 0));
                            break;
                        case "YELLOW":
                            mao_meta.setColor(Color.fromRGB(255, 255, 0));
                            yi_meta.setColor(Color.fromRGB(255, 255, 0));
                            ku_meta.setColor(Color.fromRGB(255, 255, 0));
                            xie_meta.setColor(Color.fromRGB(255, 255, 0));
                            break;
                        case "WHITE":
                            mao_meta.setColor(Color.fromRGB(255, 255, 255));
                            yi_meta.setColor(Color.fromRGB(255, 255, 255));
                            ku_meta.setColor(Color.fromRGB(255, 255, 255));
                            xie_meta.setColor(Color.fromRGB(255, 255, 255));
                            break;
                        case "AQUA":
                            mao_meta.setColor(Color.fromRGB(0, 255, 255));
                            yi_meta.setColor(Color.fromRGB(0, 255, 255));
                            ku_meta.setColor(Color.fromRGB(0, 255, 255));
                            xie_meta.setColor(Color.fromRGB(0, 255, 255));
                            break;
                        case "LIGHT_PURPLE":
                            mao_meta.setColor(Color.fromRGB(255, 105, 180));
                            yi_meta.setColor(Color.fromRGB(255, 105, 180));
                            ku_meta.setColor(Color.fromRGB(255, 105, 180));
                            xie_meta.setColor(Color.fromRGB(255, 105, 180));
                            break;
                        case "GRAY":
                            mao_meta.setColor(Color.fromRGB(190, 190, 190));
                            yi_meta.setColor(Color.fromRGB(190, 190, 190));
                            ku_meta.setColor(Color.fromRGB(190, 190, 190));
                            xie_meta.setColor(Color.fromRGB(190, 190, 190));
                            break;
                        default:
                            mao_meta.setColor(Color.fromRGB(255, 165, 0));
                            yi_meta.setColor(Color.fromRGB(255, 165, 0));
                            ku_meta.setColor(Color.fromRGB(255, 165, 0));
                            xie_meta.setColor(Color.fromRGB(255, 165, 0));
                    }

                    mao.setItemMeta(mao_meta);
                    yi.setItemMeta(yi_meta);
                    ku.setItemMeta(ku_meta);
                    xie.setItemMeta(xie_meta);

                    player.getInventory().setHelmet(mao);
                    player.getInventory().setChestplate(yi);
                    player.getInventory().setLeggings(ku);
                    player.getInventory().setBoots(xie);

                    player.getInventory().addItem(new ItemStack(Material.COMPASS));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1), true);
                    cancel();

                    String playerName = player.getName();

                    if (plugin.armorChain.contains(playerName)) {
                        if (!plugin.armorIron.contains(playerName) && !plugin.armorDiamond.contains(playerName)) {
                            player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                            player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        }
                    }

                    if (plugin.armorIron.contains(playerName)) {
                        if (!plugin.armorIron.contains(playerName)) {
                            player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                            player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                        }
                    }
                    if (plugin.armorDiamond.contains(playerName)) {
                        player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                    }
                    //End
                }
            }.runTaskLater(plugin, 1L);
        }

    }
}
