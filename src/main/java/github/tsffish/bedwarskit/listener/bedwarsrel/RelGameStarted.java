package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelArmorColor;
import github.tsffish.bedwarskit.util.RelArmorList;
import github.tsffish.bedwarskit.util.RelTeamColorName;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

import static github.tsffish.bedwarskit.util.ColorString.t;

/**
 * @author tsffish
 */
public class RelGameStarted implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);
    public static final Integer GAME_LENGTH = 3600;
    public static final String TEAM_COLOR_REPLACE = "{teamColor}";
    public static boolean customScoreboard = MainConfigHandler.customScoreboard;
    public static String scoreboardTitle = MainConfigHandler.scoreboardTitle;
    public static String rushWorld = MainConfigHandler.rushWorld;
    public static String rushWorld2v2 = MainConfigHandler.rushWorld2v2;
    public static String rushWorld4v4 = MainConfigHandler.rushWorld4v4;
    public static boolean startKitCompass = MainConfigHandler.startKitCompass;
    public static String meanTeamBedYes = MainConfigHandler.meanTeamBedYes;
    public static String meanTeamBedNo = MainConfigHandler.meanTeamBedNo;
    public static String meanTeamNone = MainConfigHandler.meanTeamNone;
    public static String serverIp = MainConfigHandler.serverIp;
    public static String RedTeamName = MainConfigHandler.relTeamName_Red;
    public static String BlueTeamName = MainConfigHandler.relTeamName_Blue;
    public static String GreenTeamName = MainConfigHandler.relTeamName_Green;
    public static String YellowTeamName = MainConfigHandler.relTeamName_Yellow;
    public static String AquaTeamName = MainConfigHandler.relTeamName_Aqua;
    public static String GrayTeamName = MainConfigHandler.relTeamName_Gray;
    public static String PinkTeamName = MainConfigHandler.relTeamName_Pink;
    public static String WhiteTeamName = MainConfigHandler.relTeamName_White;
    public static String meanYou = MainConfigHandler.meanYou;
    public static String meanNotYou = MainConfigHandler.meanNotYou;
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

    @EventHandler
    public void on(BedwarsGameStartedEvent e) {
        List<Player> p = e.getGame().getPlayers();

        if (customScoreboard) {
            e.getGame().getPlayers().forEach((player) -> {
                ScoreboardManager managerload = Bukkit.getScoreboardManager();
                Scoreboard scoreboardload = managerload.getNewScoreboard();
                Objective objload = scoreboardload.registerNewObjective("load", "dummy");
                objload.setDisplayName(t(scoreboardTitle));
                objload.setDisplaySlot(DisplaySlot.SIDEBAR);
                player.setScoreboard(scoreboardload);
            });
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getGame().getPlayers().forEach((player) -> {

                        if (player.getWorld().getName().contains(rushWorld)) {

                            if (player.getWorld().getName().contains(rushWorld4v4)) {

                                int gameTimeLeft = e.getGame().getTimeLeft();

                                ScoreboardManager manager = Bukkit.getScoreboardManager();
                                Scoreboard scoreboard = manager.getNewScoreboard();
                                Objective obj = scoreboard.registerNewObjective("4v4", "dummy");
                                obj.setDisplayName(t(scoreboardTitle));
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

                                GameManager gm = BedwarsRel.getInstance().getGameManager();

                                if (gm.getGameOfPlayer(player).getTeam(RedTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    redTeamStat = meanTeamBedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(RedTeamName).getPlayers().size() == 0) {
                                    redTeamStat = meanTeamNone;
                                } else {
                                    redTeamStat = meanTeamBedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(RedTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(BlueTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    blueTeamStat = meanTeamBedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(BlueTeamName).getPlayers().size() == 0) {
                                    blueTeamStat = meanTeamNone;
                                } else {
                                    blueTeamStat = meanTeamBedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(BlueTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(GreenTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    greenTeamStat = meanTeamBedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(GreenTeamName).getPlayers().size() == 0) {
                                    greenTeamStat = meanTeamNone;
                                } else {
                                    greenTeamStat = meanTeamBedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(GreenTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(YellowTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                    yellowTeamStat = meanTeamBedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(YellowTeamName).getPlayers().size() == 0) {
                                    yellowTeamStat = meanTeamNone;
                                } else {
                                    yellowTeamStat = meanTeamBedNo.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(YellowTeamName).getPlayers().size() + "");
                                }


                                if (redName.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    redTeamIsMe = meanYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    redTeamIsMe = meanNotYou;
                                }

                                if (yellowName.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    yellowTeamIsMe = meanYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    yellowTeamIsMe = meanNotYou;
                                }

                                if (greenName.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    greenTeamIsMe = meanYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    greenTeamIsMe = meanNotYou;
                                }

                                if (blueName.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    blueTeamIsMe = meanYou.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                                } else {
                                    blueTeamIsMe = meanNotYou;
                                }

                                String line9 = serverIp;

                                String line1Real = t(" ");
                                String line2Real = t("&a" + gameTimeLeft + "&f秒之后" + "" + "&b游戏结束");
                                String line3Real = t("   ");
                                String line4Real = t(redTeamIsMe + "&c" + RedTeamName + " " + redTeamStat);
                                String line5Real = t(blueTeamIsMe + "&9" + BlueTeamName + " " + blueTeamStat);
                                String line6Real = t(greenTeamIsMe + "&a" + GreenTeamName + " " + greenTeamStat);
                                String line7Real = t(yellowTeamIsMe + "&e" + YellowTeamName + " " + yellowTeamStat);
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

                            } else if (player.getWorld().getName().contains(rushWorld2v2)) {

                                int gameTimeLeft = e.getGame().getTimeLeft();

                                ScoreboardManager manager = Bukkit.getScoreboardManager();
                                Scoreboard scoreboard = manager.getNewScoreboard();
                                Objective obj = scoreboard.registerNewObjective("2v2", "dummy");

                                obj.setDisplayName(t(scoreboardTitle));

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

                                GameManager gm = BedwarsRel.getInstance().getGameManager();
                                Game game = gm.getGameOfPlayer(player);
                                Material bed = Material.BED_BLOCK;
                                Team playerTeam = game.getPlayerTeam(player);

                                if (game.getTeam(RedTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    redTeamStat = meanTeamBedYes;
                                } else if (game.getTeam(RedTeamName).getPlayers().size() == 0) {
                                    redTeamStat = meanTeamNone;
                                } else {
                                    redTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(RedTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(GreenTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    greenTeamStat = meanTeamBedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(GreenTeamName).getPlayers().size() == 0) {
                                    greenTeamStat = meanTeamNone;
                                } else {
                                    greenTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(GreenTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(YellowTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    yellowTeamStat = meanTeamBedYes;
                                } else if (game.getTeam(YellowTeamName).getPlayers().size() == 0) {
                                    yellowTeamStat = meanTeamNone;
                                } else {
                                    yellowTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(YellowTeamName).getPlayers().size() + "");
                                }

                                if (gm.getGameOfPlayer(player).getTeam(BlueTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    blueTeamStat = meanTeamBedYes;
                                } else if (gm.getGameOfPlayer(player).getTeam(BlueTeamName).getPlayers().size() == 0) {
                                    blueTeamStat = meanTeamNone;
                                } else {
                                    blueTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(BlueTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(AquaTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    aquaTeamStat = meanTeamBedYes;
                                } else if (game.getTeam(AquaTeamName).getPlayers().size() == 0) {
                                    aquaTeamStat = meanTeamNone;
                                } else {
                                    aquaTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(AquaTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(GrayTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    grayTeamStat = meanTeamBedYes;
                                } else if (game.getTeam(GrayTeamName).getPlayers().size() == 0) {
                                    grayTeamStat = meanTeamNone;
                                } else {
                                    grayTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(GrayTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(PinkTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    pinkTeamStat = meanTeamBedYes;
                                } else if (game.getTeam(PinkTeamName).getPlayers().size() == 0) {
                                    pinkTeamStat = meanTeamNone;
                                } else {
                                    pinkTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(PinkTeamName).getPlayers().size() + "");
                                }

                                if (game.getTeam(WhiteTeamName).getTargetHeadBlock().getBlock().getType() == bed) {
                                    whiteTeamStat = meanTeamBedYes;
                                } else if (game.getTeam(WhiteTeamName).getPlayers().size() == 0) {
                                    whiteTeamStat = meanTeamNone;
                                } else {
                                    whiteTeamStat = meanTeamBedNo.replace("{aliveCount}", game.getTeam(WhiteTeamName).getPlayers().size() + "");
                                }


                                if (redName.equals(game.getPlayerTeam(player).getColor().name())) {
                                    redTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    redTeamIsMe = meanNotYou;
                                }

                                if (yellowName.equals(game.getPlayerTeam(player).getColor().name())) {
                                    yellowTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    yellowTeamIsMe = meanNotYou;
                                }

                                if (greenName.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    greenTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    greenTeamIsMe = meanNotYou;
                                }

                                if (blueName.equals(gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name())) {
                                    blueTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE,
                                            playerTeam.getChatColor().toString());
                                } else {
                                    blueTeamIsMe = meanNotYou;
                                }

                                if (aquaName.equals(game.getPlayerTeam(player).getColor().name())) {
                                    aquaTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE,
                                            playerTeam.getChatColor().toString());
                                } else {
                                    aquaTeamIsMe = meanNotYou;
                                }

                                if (whiteName.equals(game.getPlayerTeam(player).getColor().name())) {
                                    whiteTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE,
                                            playerTeam.getChatColor().toString());
                                } else {
                                    whiteTeamIsMe = meanNotYou;
                                }

                                if (pinkName.equals(game.getPlayerTeam(player).getColor().name())) {
                                    pinkTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE,
                                            playerTeam.getChatColor().toString());
                                } else {
                                    pinkTeamIsMe = meanNotYou;
                                }

                                if (grayName.equals(game.getPlayerTeam(player).getColor().name())) {
                                    grayTeamIsMe = meanYou.replace(TEAM_COLOR_REPLACE, playerTeam.getChatColor().toString());
                                } else {
                                    grayTeamIsMe = meanNotYou;
                                }
                                String line13 = serverIp;

                                String line1Real = t(" ");
                                String line2Real = t("&a" + gameTimeLeft + "&f秒之后" + "" + "&b游戏结束");
                                String line3Real = t("  ");
                                String line4Real = t(redTeamIsMe + "&c" + RedTeamName + " " + redTeamStat);
                                String line5Real = t(blueTeamIsMe + "&9" + BlueTeamName + " " + blueTeamStat);
                                String line6Real = t(greenTeamIsMe + "&a" + GreenTeamName + " " + greenTeamStat);
                                String line7Real = t(yellowTeamIsMe + "&e" + YellowTeamName + " " + yellowTeamStat);
                                String line8Real = t(aquaTeamIsMe + "&b" + AquaTeamName + " " + aquaTeamStat);
                                String line9Real = t(grayTeamIsMe + "&7" + GrayTeamName + " " + grayTeamStat);
                                String line10Real = t(pinkTeamIsMe + "&d" + PinkTeamName + " " + pinkTeamStat);
                                String line11Real = t(whiteTeamIsMe + "&f" + WhiteTeamName + " " + whiteTeamStat);
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

                            if (player.getWorld().getName().contains(rushWorld) && gm.getGameOfPlayer(player).getTimeLeft() == GAME_LENGTH) {
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
        Game game = e.getGame();

        for (Player list: p){
            boolean isInRushWorld = list.getWorld().getName().contains(rushWorld);
            if(isInRushWorld){
            e.getGame().getPlayerSettings(list).setOneStackPerShift(true);
            String playerteam = game.getPlayerTeam(list).getColor().name();
                ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
                LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();

                String playerName = list.getName();
                RelArmorList.armorChain.remove(playerName);
                RelArmorList.armorIron.remove(playerName);
                RelArmorList.armorDiamond.remove(playerName);

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

                list.getInventory().setHelmet(helmet);
                list.getInventory().setChestplate(chestplate);
                list.getInventory().setLeggings(leggings);
                list.getInventory().setBoots(boots);

                if (startKitCompass) {
                    list.getInventory().addItem(new ItemStack(Material.COMPASS));
                }

                if (rushModeSpeed) {
                    list.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, rushModeSpeedLevel), true);
                }
            }
        }
    }
}
