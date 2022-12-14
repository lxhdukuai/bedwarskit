package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelArmorColor;
import github.tsffish.bedwarskit.util.RelArmorList;
import github.tsffish.bedwarskit.util.RelTeamColorName;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author tsffish
 */
public class RelPlayerRespawn implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);
    public static String redName = RelTeamColorName.RED_TEAM_COLOR_NAME;
    public static String blueName = RelTeamColorName.BLUE_TEAM_COLOR_NAME;
    public static String greenName = RelTeamColorName.GREEN_TEAM_COLOR_NAME;
    public static String yellowName = RelTeamColorName.YELLOW_TEAM_COLOR_NAME;
    public static String aquaName = RelTeamColorName.AQUA_TEAM_COLOR_NAME;
    public static String grayName = RelTeamColorName.GRAY_TEAM_COLOR_NAME;
    public static String pinkName = RelTeamColorName.PINK_TEAM_COLOR_NAME;
    public static String whiteName = RelTeamColorName.WHITE_TEAM_COLOR_NAME;
    public static Color redarmor = RelArmorColor.red;
    public static Color bluearmor = RelArmorColor.blue;
    public static Color greenarmor = RelArmorColor.green;
    public static Color yellowarmor = RelArmorColor.yellow;
    public static Color aquaarmor = RelArmorColor.aqua;
    public static Color grayarmor = RelArmorColor.gray;
    public static Color pinkArmor = RelArmorColor.pink;
    public static Color whiteArmor = RelArmorColor.white;
    public static boolean rushModeSpeed = MainConfigHandler.rushModeSpeed;
    public static int rushModeSpeedLevel = MainConfigHandler.rushModeSpeedLevel;
    public boolean deathGameMode = MainConfigHandler.deathGameMode;
    public String rushWorld = MainConfigHandler.rushWorld;
    public boolean startKitCompass = MainConfigHandler.startKitCompass;

    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (deathGameMode) {
            p.setGameMode(GameMode.SPECTATOR);
            boolean isInRushWorld = p.getWorld().getName().contains(rushWorld);
            if (isInRushWorld) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
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

                        if (playerteam.equals(redName)) {
                            helmetMeta.setColor(redarmor);
                            chestplateMeta.setColor(redarmor);
                            leggingsMeta.setColor(redarmor);
                            bootsMeta.setColor(redarmor);
                        } else if (playerteam.equals(blueName)) {
                            helmetMeta.setColor(bluearmor);
                            chestplateMeta.setColor(bluearmor);
                            leggingsMeta.setColor(bluearmor);
                            bootsMeta.setColor(bluearmor);
                        } else if (playerteam.equals(greenName)) {
                            helmetMeta.setColor(greenarmor);
                            chestplateMeta.setColor(greenarmor);
                            leggingsMeta.setColor(greenarmor);
                            bootsMeta.setColor(greenarmor);
                        } else if (playerteam.equals(yellowName)) {
                            helmetMeta.setColor(yellowarmor);
                            chestplateMeta.setColor(yellowarmor);
                            leggingsMeta.setColor(yellowarmor);
                            bootsMeta.setColor(yellowarmor);
                        } else if (playerteam.equals(aquaName)) {
                            helmetMeta.setColor(aquaarmor);
                            chestplateMeta.setColor(aquaarmor);
                            leggingsMeta.setColor(aquaarmor);
                            bootsMeta.setColor(aquaarmor);
                        } else if (playerteam.equals(grayName)) {
                            helmetMeta.setColor(grayarmor);
                            chestplateMeta.setColor(grayarmor);
                            leggingsMeta.setColor(grayarmor);
                            bootsMeta.setColor(grayarmor);
                        } else if (playerteam.equals(pinkName)) {
                            helmetMeta.setColor(pinkArmor);
                            chestplateMeta.setColor(pinkArmor);
                            leggingsMeta.setColor(pinkArmor);
                            bootsMeta.setColor(pinkArmor);
                        } else if (playerteam.equals(whiteName)) {
                            helmetMeta.setColor(whiteArmor);
                            chestplateMeta.setColor(whiteArmor);
                            leggingsMeta.setColor(whiteArmor);
                            bootsMeta.setColor(whiteArmor);
                        }


                        helmet.setItemMeta(helmetMeta);
                        chestplate.setItemMeta(chestplateMeta);
                        leggings.setItemMeta(leggingsMeta);
                        boots.setItemMeta(bootsMeta);

                        player.getInventory().setHelmet(helmet);
                        player.getInventory().setChestplate(chestplate);
                        player.getInventory().setLeggings(leggings);
                        player.getInventory().setBoots(boots);


                        if (startKitCompass) {
                            player.getInventory().addItem(new ItemStack(Material.COMPASS));
                        }

                        if (rushModeSpeed) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, rushModeSpeedLevel), true);
                        }
                        cancel();

                        String playerName = player.getName();

                        if (RelArmorList.armorChain.contains(playerName)) {
                            if (!RelArmorList.armorIron.contains(playerName) && !RelArmorList.armorDiamond.contains(playerName)) {
                                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                                player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                            }
                        }

                        if (RelArmorList.armorIron.contains(playerName)) {
                            if (!RelArmorList.armorIron.contains(playerName)) {
                                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                            }
                        }
                        if (RelArmorList.armorDiamond.contains(playerName)) {
                            player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                        }
                        //End
                    }
                }.runTaskLater(plugin, 1L);
            }
        }
    }
}
