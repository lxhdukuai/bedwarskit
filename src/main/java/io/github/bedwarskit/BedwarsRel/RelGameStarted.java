package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
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

import static io.github.bedwarskit.Util.ColorString.t;

public class RelGameStarted implements Listener {
    private final Main plugin;
    public RelGameStarted(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(BedwarsGameStartedEvent e) {
        if (plugin.custom_scoreboard) {

            e.getGame().getPlayers().forEach((player) -> {
                ScoreboardManager managerload = Bukkit.getScoreboardManager();
            Scoreboard scoreboardload = managerload.getNewScoreboard();
            Objective objload = scoreboardload.registerNewObjective("load", "dummy");

            objload.setDisplayName(t(plugin.scoreboard_title));

            objload.setDisplaySlot(DisplaySlot.SIDEBAR);
            player.setScoreboard(scoreboardload);
        });
            new BukkitRunnable() {
                @Override
                public void run() {

                    e.getGame().getPlayers().forEach((player) -> {

                        if (player.getWorld().getName().contains(plugin.rushWorld)){

                        if (player.getWorld().getName().contains(plugin.rushWorld4v4)) {

                            int gameCD = e.getGame().getTimeLeft();

                            ScoreboardManager manager = Bukkit.getScoreboardManager();
                            Scoreboard scoreboard = manager.getNewScoreboard();
                            Objective obj = scoreboard.registerNewObjective("4v4", "dummy");

                            obj.setDisplayName(t(plugin.scoreboard_title));

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
                                redTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(redTeamName).getPlayers().size() == 0) {
                                redTeamStat = plugin.meaning_team_none;
                            } else {
                                redTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(redTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(yellowTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                yellowTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(yellowTeamName).getPlayers().size() == 0) {
                                yellowTeamStat = plugin.meaning_team_none;
                            } else {
                                yellowTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(yellowTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                greenTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() == 0) {
                                greenTeamStat = plugin.meaning_team_none;
                            } else {
                                greenTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                blueTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() == 0) {
                                blueTeamStat = plugin.meaning_team_none;
                            } else {
                                blueTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() + "");
                            }


                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("RED")) {
                                redTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                redTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("YELLOW")) {
                                yellowTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                yellowTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("GREEN")) {
                                greenTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                greenTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("BLUE")) {
                                blueTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                blueTeamIsMe = plugin.meaning_NotYou;
                            }

                            String line9 = plugin.line9_4v4;

                            String line1_real = t(" ");
                            String line2_real = t("&a" + gameCD + "&f秒之后" + "" + "&b游戏结束");
                            String line3_real = t("   ");
                            String line4_real = t(redTeamIsMe + "&c" + redTeamName + " " + redTeamStat);
                            String line5_real = t(yellowTeamIsMe + "&e" + yellowTeamName + " " + yellowTeamStat);
                            String line6_real = t(greenTeamIsMe + "&a" + greenTeamName + " " + greenTeamStat);
                            String line7_real = t(blueTeamIsMe + "&9" + blueTeamName + " " + blueTeamStat);
                            String line8_real = t("    ");
                            String line9_real = t(line9);

                            obj.getScore(line1_real).setScore(8);
                            obj.getScore(line2_real).setScore(7);
                            obj.getScore(line3_real).setScore(6);
                            obj.getScore(line4_real).setScore(5);
                            obj.getScore(line5_real).setScore(4);
                            obj.getScore(line6_real).setScore(3);
                            obj.getScore(line7_real).setScore(2);
                            obj.getScore(line8_real).setScore(1);
                            obj.getScore(line9_real).setScore(0);

                        } else if (player.getWorld().getName().contains(plugin.rushWorld2v2)) {

                            int gameCD = e.getGame().getTimeLeft();

                            ScoreboardManager manager = Bukkit.getScoreboardManager();
                            Scoreboard scoreboard = manager.getNewScoreboard();
                            Objective obj = scoreboard.registerNewObjective("2v2", "dummy");

                            obj.setDisplayName(t(plugin.scoreboard_title));

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

                            if (gm.getGameOfPlayer(player).getTeam(redTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                redTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(redTeamName).getPlayers().size() == 0) {
                                redTeamStat = plugin.meaning_team_none;
                            } else {
                                redTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(redTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(yellowTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                yellowTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(yellowTeamName).getPlayers().size() == 0) {
                                yellowTeamStat = plugin.meaning_team_none;
                            } else {
                                yellowTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(yellowTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                greenTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() == 0) {
                                greenTeamStat = plugin.meaning_team_none;
                            } else {
                                greenTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(greenTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                blueTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() == 0) {
                                blueTeamStat = plugin.meaning_team_none;
                            } else {
                                blueTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(blueTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(aquaTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                aquaTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(aquaTeamName).getPlayers().size() == 0) {
                                aquaTeamStat = plugin.meaning_team_none;
                            } else {
                                aquaTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(aquaTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(whiteTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                whiteTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(whiteTeamName).getPlayers().size() == 0) {
                                whiteTeamStat = plugin.meaning_team_none;
                            } else {
                                whiteTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(whiteTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(pinkTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                pinkTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(pinkTeamName).getPlayers().size() == 0) {
                                pinkTeamStat = plugin.meaning_team_none;
                            } else {
                                pinkTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(pinkTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getTeam(grayTeamName).getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                grayTeamStat = plugin.meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam(grayTeamName).getPlayers().size() == 0) {
                                grayTeamStat = plugin.meaning_team_none;
                            } else {
                                grayTeamStat = plugin.meaning_teambed_no.replace("{aliveCount}", gm.getGameOfPlayer(player).getTeam(grayTeamName).getPlayers().size() + "");
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("RED")) {
                                redTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                redTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("YELLOW")) {
                                yellowTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                yellowTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("GREEN")) {
                                greenTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                greenTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("BLUE")) {
                                blueTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                blueTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("AQUA")) {
                                aquaTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                aquaTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("WHITE")) {
                                whiteTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                whiteTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("LIGHT_PURPLE")) {
                                pinkTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                pinkTeamIsMe = plugin.meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("GRAY")) {
                                grayTeamIsMe = plugin.meaning_You.replace("{teamColor}", gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            } else {
                                grayTeamIsMe = plugin.meaning_NotYou;
                            }


                            String line13 = plugin.line13_2v2;

                            String line1_real = t(" ");
                            String line2_real = t("&a" + gameCD + "&f秒之后" + "" + "&b游戏结束");
                            String line3_real = t("  ");
                            String line4_real = t(redTeamIsMe + "&e" + redTeamName + " " + redTeamStat);
                            String line5_real = t(yellowTeamIsMe + "&e" + yellowTeamName + " " + yellowTeamStat);
                            String line6_real = t(greenTeamIsMe + "&a" + greenTeamName + " " + greenTeamStat);
                            String line7_real = t(blueTeamIsMe + "&9" + blueTeamName + " " + blueTeamStat);
                            String line8_real = t(aquaTeamIsMe + "&b" + aquaTeamName + " " + aquaTeamStat);
                            String line9_real = t(whiteTeamIsMe + "&f" + whiteTeamName + " " + whiteTeamStat);
                            String line10_real = t(pinkTeamIsMe + "&d" + pinkTeamName + " " + pinkTeamStat);
                            String line11_real = t(grayTeamIsMe + "&7" + grayTeamName + " " + grayTeamStat);
                            String line12_real = t("   ");
                            String line13_real = t(line13);

                            obj.getScore(line1_real).setScore(12);
                            obj.getScore(line2_real).setScore(11);
                            obj.getScore(line3_real).setScore(10);
                            obj.getScore(line4_real).setScore(9);
                            obj.getScore(line5_real).setScore(8);
                            obj.getScore(line6_real).setScore(7);
                            obj.getScore(line7_real).setScore(6);
                            obj.getScore(line8_real).setScore(5);
                            obj.getScore(line9_real).setScore(4);
                            obj.getScore(line10_real).setScore(3);
                            obj.getScore(line11_real).setScore(2);
                            obj.getScore(line12_real).setScore(1);
                            obj.getScore(line13_real).setScore(0);

                        }

                        GameManager gm = BedwarsRel.getInstance().getGameManager();

                        if (player.getWorld().getName().contains("rush") && gm.getGameOfPlayer(player).getTimeLeft() == 3600) {
                            cancel();
                            player.setScoreboard(BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player).getScoreboard());
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

            ItemStack mao = new ItemStack(Material.LEATHER_HELMET);
            ItemStack yi = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStack ku = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStack xie = new ItemStack(Material.LEATHER_BOOTS);

            LeatherArmorMeta mao_meta = (LeatherArmorMeta) mao.getItemMeta();
            LeatherArmorMeta yi_meta = (LeatherArmorMeta) yi.getItemMeta();
            LeatherArmorMeta ku_meta = (LeatherArmorMeta) ku.getItemMeta();
            LeatherArmorMeta xie_meta = (LeatherArmorMeta) xie.getItemMeta();

            String playerName = player.getName();
            plugin.armorChain.remove(playerName);
            plugin.armorIron.remove(playerName);
            plugin.armorDiamond.remove(playerName);

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
        });
    }
}
