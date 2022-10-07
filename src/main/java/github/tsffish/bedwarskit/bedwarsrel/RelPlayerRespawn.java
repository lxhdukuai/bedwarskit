package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
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

/**
 * @author Administrator
 */
public class RelPlayerRespawn implements Listener {
    private final Main plugin;

    public RelPlayerRespawn(Main plugin) {
        this.plugin = plugin;
    }

    public static final String RED_TEAM_COLOR_NAME = "RED";
    public static final String YELLOW_TEAM_COLOR_NAME = "YELLOW";
    public static final String GREEN_TEAM_COLOR_NAME = "GREEN";
    public static final String BLUE_TEAM_COLOR_NAME = "BLUE";
    public static final String PINK_TEAM_COLOR_NAME = "LIGHT_PURPLE";
    public static final String AQUA_TEAM_COLOR_NAME = "AQUA";
    public static final String WHITE_TEAM_COLOR_NAME = "WHITE";
    public static final String GRAY_TEAM_COLOR_NAME = "GRAY";

    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (p.getWorld().getName().contains(plugin.rushWorld) && plugin.deathGamemode) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.setGameMode(GameMode.SPECTATOR);
                    Player player = e.getPlayer();
                    Game game = gm.getGameOfPlayer(player);
                    String playerteam = game.getPlayerTeam(player).getColor().name();

                    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

                    LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
                    LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
                    LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                    LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                    switch (playerteam) {
                        case RED_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(255, 0, 0));
                            chestplateMeta.setColor(Color.fromRGB(255, 0, 0));
                            leggingsMeta.setColor(Color.fromRGB(255, 0, 0));
                            bootsMeta.setColor(Color.fromRGB(255, 0, 0));
                            break;
                        case BLUE_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(0, 0, 255));
                            chestplateMeta.setColor(Color.fromRGB(0, 0, 255));
                            leggingsMeta.setColor(Color.fromRGB(0, 0, 255));
                            bootsMeta.setColor(Color.fromRGB(0, 0, 255));
                            break;
                        case GREEN_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(0, 255, 0));
                            chestplateMeta.setColor(Color.fromRGB(0, 255, 0));
                            leggingsMeta.setColor(Color.fromRGB(0, 255, 0));
                            bootsMeta.setColor(Color.fromRGB(0, 255, 0));
                            break;
                        case YELLOW_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(255, 255, 0));
                            chestplateMeta.setColor(Color.fromRGB(255, 255, 0));
                            leggingsMeta.setColor(Color.fromRGB(255, 255, 0));
                            bootsMeta.setColor(Color.fromRGB(255, 255, 0));
                            break;
                        case WHITE_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(255, 255, 255));
                            chestplateMeta.setColor(Color.fromRGB(255, 255, 255));
                            leggingsMeta.setColor(Color.fromRGB(255, 255, 255));
                            bootsMeta.setColor(Color.fromRGB(255, 255, 255));
                            break;
                        case AQUA_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(0, 255, 255));
                            chestplateMeta.setColor(Color.fromRGB(0, 255, 255));
                            leggingsMeta.setColor(Color.fromRGB(0, 255, 255));
                            bootsMeta.setColor(Color.fromRGB(0, 255, 255));
                            break;
                        case PINK_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(255, 105, 180));
                            chestplateMeta.setColor(Color.fromRGB(255, 105, 180));
                            leggingsMeta.setColor(Color.fromRGB(255, 105, 180));
                            bootsMeta.setColor(Color.fromRGB(255, 105, 180));
                            break;
                        case GRAY_TEAM_COLOR_NAME:
                            helmetMeta.setColor(Color.fromRGB(190, 190, 190));
                            chestplateMeta.setColor(Color.fromRGB(190, 190, 190));
                            leggingsMeta.setColor(Color.fromRGB(190, 190, 190));
                            bootsMeta.setColor(Color.fromRGB(190, 190, 190));
                            break;
                        default:
                            helmetMeta.setColor(Color.fromRGB(255, 165, 0));
                            chestplateMeta.setColor(Color.fromRGB(255, 165, 0));
                            leggingsMeta.setColor(Color.fromRGB(255, 165, 0));
                            bootsMeta.setColor(Color.fromRGB(255, 165, 0));
                    }

                    helmet.setItemMeta(helmetMeta);
                    chestplate.setItemMeta(chestplateMeta);
                    leggings.setItemMeta(leggingsMeta);
                    boots.setItemMeta(bootsMeta);

                    player.getInventory().setHelmet(helmet);
                    player.getInventory().setChestplate(chestplate);
                    player.getInventory().setLeggings(leggings);
                    player.getInventory().setBoots(boots);

                    if (plugin.startKitCompass) {
                        player.getInventory().addItem(new ItemStack(Material.COMPASS));
                    }
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
