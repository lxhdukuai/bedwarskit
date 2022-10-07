package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import static github.tsffish.bedwarskit.util.ColorString.t;

/**
 * @author Administrator
 */
public class RelGameStarted implements Listener {
    private final Main plugin;

    public RelGameStarted(Main plugin) {
        this.plugin = plugin;
    }

    public static final Integer GAME_LENGTH = 3600;
    public static final String RED_TEAM_COLOR_NAME = "RED";
    public static final String YELLOW_TEAM_COLOR_NAME = "YELLOW";
    public static final String GREEN_TEAM_COLOR_NAME = "GREEN";
    public static final String BLUE_TEAM_COLOR_NAME = "BLUE";
    public static final String PINK_TEAM_COLOR_NAME = "LIGHT_PURPLE";
    public static final String AQUA_TEAM_COLOR_NAME = "AQUA";
    public static final String WHITE_TEAM_COLOR_NAME = "WHITE";
    public static final String GRAY_TEAM_COLOR_NAME = "GRAY";
    public static final String TEAM_COLOR_REPLACE = "{teamColor}";

    @EventHandler
    public void on(BedwarsGameStartedEvent e) {
        if (plugin.customScoreboard) {
            e.getGame().getPlayers().forEach((player) -> {
                ScoreboardManager managerload = Bukkit.getScoreboardManager();
                Scoreboard scoreboardload = managerload.getNewScoreboard();
                Objective objload = scoreboardload.registerNewObjective("load", "dummy");
                objload.setDisplayName(t(plugin.scoreboardTitle));
                objload.setDisplaySlot(DisplaySlot.SIDEBAR);
                player.setScoreboard(scoreboardload);
            });
            new BukkitRunnable() {
                @Override
                public void run() {

                    e.getGame().getPlayers().forEach((player) -> {

                        if (player.getWorld().getName().contains(plugin.rushWorld)) {

                            if (player.getWorld().getName().contains(plugin.rushWorld4v4)) {

                                int gameTimeLeft = e.getGame().getTimeLeft();

                                ScoreboardManager manager = Bukkit.getScoreboardManager();
                                Scoreboard scoreboard = manager.getNewScoreboard();
                                Objective obj = scoreboard.registerNewObjective("4v4", "dummy");
                                obj.setDisplayName(t(plugin.scoreboardTitle));
                                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                                player.setScoreboard(scoreboard);
                                String redTeamStat;
                                String yellowTeamStat;
                                String greenTeamStat;
                                String blueTeamStat;

                                String redTeamIsMe;
                                String yellowTeamIsMe;
                                String greenTeamIsMe;
                                String blueTeamIsMe;

                                String redTeamName = plugin.relTeamRedName;
                                String yellowTeamName = plugin.relTeamYellowName;
                                String greenTeamName = plugin.relTeamGreenName;
                                String blueTeamName = plugin.relTeamBlueName;

                                GameManager gm = BedwarsRel.getInstance().getGameManager();

                                if (gm.getGameOfPlayer(player).getTeam(redTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    redTeamStat = plugin.meaningTeambedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(redTeamName).getPlayers().size() == 0) {
                                    redTeamStat = plugin.meaningTeamNone;
                                } else {
                                    redTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(redTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(yellowTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    yellowTeamStat = plugin.meaningTeambedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(yellowTeamName).getPlayers().size() == 0) {
                                    yellowTeamStat = plugin.meaningTeamNone;
                                } else {
                                    yellowTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(yellowTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    greenTeamStat = plugin.meaningTeambedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() == 0) {
                                    greenTeamStat = plugin.meaningTeamNone;
                                } else {
                                    greenTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    blueTeamStat = plugin.meaningTeambedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() == 0) {
                                    blueTeamStat = plugin.meaningTeamNone;
                                } else {
                                    blueTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() + "");
                                }


                                if (RED_TEAM_COLOR_NAME.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    redTeamIsMe = plugin.meaningYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    redTeamIsMe = plugin.meaningNotYou;
                                }

                                if (YELLOW_TEAM_COLOR_NAME.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    yellowTeamIsMe = plugin.meaningYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    yellowTeamIsMe = plugin.meaningNotYou;
                                }

                                if (GREEN_TEAM_COLOR_NAME.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    greenTeamIsMe = plugin.meaningYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    greenTeamIsMe = plugin.meaningNotYou;
                                }

                                if (BLUE_TEAM_COLOR_NAME.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    blueTeamIsMe = plugin.meaningYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    blueTeamIsMe = plugin.meaningNotYou;
                                }

                                String line9 = plugin.line94v4;

                                String line1Real = t(" ");
                                String line2Real = t("&a" + gameTimeLeft + "&f秒之后" + "" + "&b游戏结束");
                                String line3Real = t("   ");
                                String line4Real = t(redTeamIsMe + "&c" + redTeamName + " " + redTeamStat);
                                String line5Real = t(yellowTeamIsMe + "&e" + yellowTeamName + " " + yellowTeamStat);
                                String line6Real = t(greenTeamIsMe + "&a" + greenTeamName + " " + greenTeamStat);
                                String line7Real = t(blueTeamIsMe + "&9" + blueTeamName + " " + blueTeamStat);
                                String line8Real = t("    ");
                                String line9Real = t(line9);

                                obj.getScore(line1Real).setScore(8);
                                obj.getScore(line2Real).setScore(7);
                                obj.getScore(line3Real).setScore(6);
                                obj.getScore(line4Real).setScore(5);
                                obj.getScore(line5Real).setScore(4);
                                obj.getScore(line6Real).setScore(3);
                                obj.getScore(line7Real).setScore(2);
                                obj.getScore(line8Real).setScore(1);
                                obj.getScore(line9Real).setScore(0);

                            } else if (player.getWorld().getName().contains(plugin.rushWorld2v2)) {

                                int gameTimeLeft = e.getGame().getTimeLeft();

                                ScoreboardManager manager = Bukkit.getScoreboardManager();
                                Scoreboard scoreboard = manager.getNewScoreboard();
                                Objective obj = scoreboard.registerNewObjective("2v2", "dummy");

                                obj.setDisplayName(t(plugin.scoreboardTitle));

                                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                                player.setScoreboard(scoreboard);

                                String redTeamStat;
                                String yellowTeamStat;
                                String greenTeamStat;
                                String blueTeamStat;
                                String aquaTeamStat;
                                String whiteTeamStat;
                                String pinkTeamStat;
                                String grayTeamStat;

                                String redTeamIsMe;
                                String yellowTeamIsMe;
                                String greenTeamIsMe;
                                String blueTeamIsMe;
                                String aquaTeamIsMe;
                                String whiteTeamIsMe;
                                String pinkTeamIsMe;
                                String grayTeamIsMe;

                                String redTeamName = plugin.relTeamRedName;
                                String yellowTeamName = plugin.relTeamYellowName;
                                String greenTeamName = plugin.relTeamGreenName;
                                String blueTeamName = plugin.relTeamBlueName;
                                String aquaTeamName = plugin.relTeamAquaName;
                                String whiteTeamName = plugin.relTeamWhiteName;
                                String pinkTeamName = plugin.relTeamPinkName;
                                String grayTeamName = plugin.relTeamGrayName;

                                GameManager gm = BedwarsRel.getInstance().getGameManager();
                                Game game = gm.getGameOfPlayer(player);
                                Material bed = Material.BED_BLOCK;
                                Team playerTeam = game.getPlayerTeam(player);

                                if (game.getTeam(redTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    redTeamStat = plugin.meaningTeambedYes;
                                } else if (game.getTeam(redTeamName).getPlayers().size() == 0) {
                                    redTeamStat = plugin.meaningTeamNone;
                                } else {
                                    redTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(redTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(yellowTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    yellowTeamStat = plugin.meaningTeambedYes;
                                } else if (game.getTeam(yellowTeamName).getPlayers().size() == 0) {
                                    yellowTeamStat = plugin.meaningTeamNone;
                                } else {
                                    yellowTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(yellowTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    greenTeamStat = plugin.meaningTeambedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() == 0) {
                                    greenTeamStat = plugin.meaningTeamNone;
                                } else {
                                    greenTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(greenTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    blueTeamStat = plugin.meaningTeambedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() == 0) {
                                    blueTeamStat = plugin.meaningTeamNone;
                                } else {
                                    blueTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(blueTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(aquaTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    aquaTeamStat = plugin.meaningTeambedYes;
                                } else if (game.getTeam(aquaTeamName).getPlayers().size() == 0) {
                                    aquaTeamStat = plugin.meaningTeamNone;
                                } else {
                                    aquaTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(aquaTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(whiteTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    whiteTeamStat = plugin.meaningTeambedYes;
                                } else if (game.getTeam(whiteTeamName).getPlayers().size() == 0) {
                                    whiteTeamStat = plugin.meaningTeamNone;
                                } else {
                                    whiteTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(whiteTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(pinkTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    pinkTeamStat = plugin.meaningTeambedYes;
                                } else if (game.getTeam(pinkTeamName).getPlayers().size() == 0) {
                                    pinkTeamStat = plugin.meaningTeamNone;
                                } else {
                                    pinkTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(pinkTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(grayTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    grayTeamStat = plugin.meaningTeambedYes;
                                } else if (game.getTeam(grayTeamName).getPlayers().size() == 0) {
                                    grayTeamStat = plugin.meaningTeamNone;
                                } else {
                                    grayTeamStat = plugin.meaningTeambedNo.replace("{aliveCount}", game.getTeam(grayTeamName).getPlayers().size() + "");
                                }

                                if (RED_TEAM_COLOR_NAME.equals(game.getPlayerTeam(player).getColor().name())) {
                                    redTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    redTeamIsMe = plugin.meaningNotYou;
                                }

                                if (YELLOW_TEAM_COLOR_NAME.equals(game.getPlayerTeam(player).getColor().name())) {
                                    yellowTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    yellowTeamIsMe = plugin.meaningNotYou;
                                }

                                if (GREEN_TEAM_COLOR_NAME.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    greenTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    greenTeamIsMe = plugin.meaningNotYou;
                                }

                                if (BLUE_TEAM_COLOR_NAME.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    blueTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    blueTeamIsMe = plugin.meaningNotYou;
                                }

                                if (AQUA_TEAM_COLOR_NAME.equals(game.getPlayerTeam(player).getColor().name())) {
                                    aquaTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    aquaTeamIsMe = plugin.meaningNotYou;
                                }

                                if (WHITE_TEAM_COLOR_NAME.equals(game.getPlayerTeam(player).getColor().name())) {
                                    whiteTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    whiteTeamIsMe = plugin.meaningNotYou;
                                }

                                if (PINK_TEAM_COLOR_NAME.equals(game.getPlayerTeam(player).getColor().name())) {
                                    pinkTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    pinkTeamIsMe = plugin.meaningNotYou;
                                }

                                if (GRAY_TEAM_COLOR_NAME.equals(game.getPlayerTeam(player).getColor().name())) {
                                    grayTeamIsMe = plugin.meaningYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    grayTeamIsMe = plugin.meaningNotYou;
                                }
                                String line13 = plugin.line132v2;

                                String line1Real = t(" ");
                                String line2Real = t("&a" + gameTimeLeft + "&f秒之后" + "" + "&b游戏结束");
                                String line3Real = t("  ");
                                String line4Real = t(redTeamIsMe + "&e" + redTeamName + " " + redTeamStat);
                                String line5Real = t(yellowTeamIsMe + "&e" + yellowTeamName + " " + yellowTeamStat);
                                String line6Real = t(greenTeamIsMe + "&a" + greenTeamName + " " + greenTeamStat);
                                String line7Real = t(blueTeamIsMe + "&9" + blueTeamName + " " + blueTeamStat);
                                String line8Real = t(aquaTeamIsMe + "&b" + aquaTeamName + " " + aquaTeamStat);
                                String line9Real = t(whiteTeamIsMe + "&f" + whiteTeamName + " " + whiteTeamStat);
                                String line10Real = t(pinkTeamIsMe + "&d" + pinkTeamName + " " + pinkTeamStat);
                                String line11Real = t(grayTeamIsMe + "&7" + grayTeamName + " " + grayTeamStat);
                                String line12Real = t("   ");
                                String line13Real = t(line13);

                                obj.getScore(line1Real).setScore(12);
                                obj.getScore(line2Real).setScore(11);
                                obj.getScore(line3Real).setScore(10);
                                obj.getScore(line4Real).setScore(9);
                                obj.getScore(line5Real).setScore(8);
                                obj.getScore(line6Real).setScore(7);
                                obj.getScore(line7Real).setScore(6);
                                obj.getScore(line8Real).setScore(5);
                                obj.getScore(line9Real).setScore(4);
                                obj.getScore(line10Real).setScore(3);
                                obj.getScore(line11Real).setScore(2);
                                obj.getScore(line12Real).setScore(1);
                                obj.getScore(line13Real).setScore(0);

                            }

                            GameManager gm = BedwarsRel.getInstance().getGameManager();

                            if (player.getWorld().getName().contains(plugin.rushWorld) && gm.getGameOfPlayer(player).getTimeLeft() == GAME_LENGTH) {
                                cancel();

                                Game game = gm.getGameOfPlayer(player);

                                Scoreboard relScoreBoard = game.getScoreboard();
                                player.setScoreboard(relScoreBoard);
                            }
                        }
                    });

                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
        e.getGame().getPlayers().forEach((player) -> {
            Game game = e.getGame();

            e.getGame().getPlayerSettings(player).setOneStackPerShift(true);

            String playerteam = game.getPlayerTeam(player).getColor().name();

            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();

            String playerName = player.getName();
            plugin.armorChain.remove(playerName);
            plugin.armorIron.remove(playerName);
            plugin.armorDiamond.remove(playerName);

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
        });
    }
}
