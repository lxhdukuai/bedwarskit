package io.github.bedwarskit;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.events.BedwarsPlayerJoinedEvent;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;
import java.util.logging.Logger;

public class bedwarskit extends JavaPlugin implements Listener {
    private Logger l;
    public bedwarskit instance;
    public  FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        l = getLogger();
        instance = this;
        l.warning("正在启用");
        PluginManager pm = Bukkit.getPluginManager();
        saveDefaultConfig();
        pm.registerEvents(this,this);
    }
    public static String t(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }
    @Override
    public void onDisable() {l.warning("正在卸载");l=null;}
    // public List<String> NofallDamage = new ArrayList<>();
    public List<String> ArmorChain = new ArrayList<>();
    public List<String> ArmorIron = new ArrayList<>();
    public List<String> ArmorDiamond = new ArrayList<>();
    public boolean breaktitle = config.getBoolean("break_bed_title");
    public String break_title_all = config.getString("break_title_all");
    public String break_subtitle_all = config.getString("break_subtitle_all");
    public String break_title_breakPlayer = config.getString("break_title_breakPlayer");
    public String break_subtitle_breakPlayer = config.getString("break_subtitle_breakPlayer");
    public String break_title_breakTeam = config.getString("break_title_breakTeam");
    public String break_subtitle_breakTeam = config.getString("break_subtitle_breakTeam");
    public boolean nohunger = config.getBoolean("no_hunger");
    public boolean no_pearl_damage = config.getBoolean("no_pearl_damage");
    public boolean break_bed_check = config.getBoolean("break_bed_check");
    public boolean custom_scoreboard = config.getBoolean("custom_scoreboard");
    public double tp_dis = config.getDouble("tp_dis");
    public boolean death_gamemode = config.getBoolean("death_gamemode");
    public String respawn_title = config.getString("respawn_title");
    public String respawn_subtitle = config.getString("respawn_subtitle");
    public String respawn_succ_title = config.getString("respawn_succ_title");
    public String respawn_succ_subtitle = config.getString("respawn_succ_subtitle");
    public String meaning_teambed_yes = config.getString("meaning_teambed_yes");
    public String meaning_team_none = config.getString("meaning_team_none");
    public String meaning_teambed_no = config.getString("meaning_teambed_no");
    public String scoreboard_title = config.getString("scoreboard_title");
    public String meaning_You = config.getString("meaning_You");
    public String meaning_NotYou = config.getString("meaning_NotYou");
    public String rushWorld = config.getString("rushworld");
    @EventHandler
    public void on(InventoryClickEvent e){
        if(e.getCurrentItem().getType() == Material.LEATHER_HELMET || e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE ||
                e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS || e.getCurrentItem().getType() == Material.LEATHER_BOOTS ||
                e.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS || e.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS ||
                e.getCurrentItem().getType() == Material.IRON_LEGGINGS || e.getCurrentItem().getType() == Material.IRON_LEGGINGS ||
                e.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS || e.getCurrentItem().getType() == Material.DIAMOND_BOOTS){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(PlayerReSpawnEvent e){
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void on(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();
        if (death_gamemode && e.getEntity().getWorld().getName().contains("rush")) {
            Location spawnloc = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();
            Plugin plugin = this;
            new BukkitRunnable() {
                int x = 5;

                @Override
                public void run() {
                    if (x != 0) {
                        x--;
                        String i = Integer.toString(x);
                        String respawn_title_real = respawn_title.replace("{timeleft}", i);
                        String respawn_subtitle_real = respawn_subtitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawn_title_real), t(respawn_subtitle_real));
                    }
                    if (x == 0) {
                        String i = Integer.toString(x);
                        String respawn_succ_title_real = respawn_succ_title.replace("{timeleft}", i);
                        String respawn_succ_subtitle_real = respawn_succ_subtitle.replace("{timeleft}", i);
                        p.sendTitle(t(respawn_succ_title_real), t(respawn_succ_subtitle_real));
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(spawnloc);
                        cancel();
                    }
                    if (!p.getWorld().getName().contains(rushWorld) || BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getTimeLeft() == 3600) {
                        p.setGameMode(GameMode.SURVIVAL);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 20L, 20L);
        }
    }
    @EventHandler
    public void on(BedwarsGameStartedEvent e) {

        if (custom_scoreboard) {
            Plugin plugin = this;
            new BukkitRunnable() {
                @Override
                public void run() {

                    e.getGame().getPlayers().forEach((player) -> {
                        ScoreboardManager managerload = Bukkit.getScoreboardManager();
                        Scoreboard scoreboardload = managerload.getNewScoreboard();
                        Objective objload = scoreboardload.registerNewObjective("load", "dummy");

                        objload.setDisplayName(t(scoreboard_title));

                        objload.setDisplaySlot(DisplaySlot.SIDEBAR);
                        player.setScoreboard(scoreboardload);

                        if (player.getWorld().getName().contains("4v4rush")){

                            int gameCD = e.getGame().getTimeLeft();

                            ScoreboardManager manager = Bukkit.getScoreboardManager();
                            Scoreboard scoreboard = manager.getNewScoreboard();
                            Objective obj = scoreboard.registerNewObjective("4v4", "dummy");

                            obj.setDisplayName(t(scoreboard_title));

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

                            if (gm.getGameOfPlayer(player).getTeam("红之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                redTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("红之队").getPlayers().size() == 0) {
                                redTeamStat = meaning_team_none;
                            } else {
                                redTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("红之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("黄之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                yellowTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("黄之队").getPlayers().size() == 0) {
                                yellowTeamStat = meaning_team_none;
                            } else {
                                yellowTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("黄之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("绿之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                greenTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("绿之队").getPlayers().size() == 0) {
                                greenTeamStat = meaning_team_none;
                            } else {
                                greenTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("绿之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("蓝之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                blueTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("蓝之队").getPlayers().size() == 0) {
                                blueTeamStat = meaning_team_none;
                            } else {
                                blueTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("蓝之队").getPlayers().size()+"");
                            }


                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("RED")){
                                redTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                redTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("YELLOW")){
                                yellowTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                yellowTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("GREEN")){
                                greenTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                greenTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("BLUE")){
                                blueTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                blueTeamIsMe = meaning_NotYou;
                            }

                            String emptya = t(" ");
                            String gameTask = t("&a" + gameCD + "&f秒之后" + "" + "&b游戏结束");
                            String emptyb = t("   ");
                            String redTeam = t( redTeamIsMe + " &c" + "红之队 " + redTeamStat);
                            String yellowTeam = t( yellowTeamIsMe + " &e" + "黄之队 " + yellowTeamStat);
                            String greenTeam = t( greenTeamIsMe + " &a" + "绿之队 "  +  greenTeamStat);
                            String blueTeam = t(blueTeamIsMe + " &9" + "蓝之队 "  + blueTeamStat);
                            String emptyc = t("    ");
                            String ip = t("&emc.bedwars.org");

                            obj.getScore(emptya).setScore(8);
                            obj.getScore(gameTask).setScore(7);
                            obj.getScore(emptyb).setScore(6);
                            obj.getScore(redTeam).setScore(5);
                            obj.getScore(yellowTeam).setScore(4);
                            obj.getScore(greenTeam).setScore(3);
                            obj.getScore(blueTeam).setScore(2);
                            obj.getScore(emptyc).setScore(1);
                            obj.getScore(ip).setScore(0);

                        } else if (player.getWorld().getName().contains("2v2rush")) {

                            int gameCD = e.getGame().getTimeLeft();

                            ScoreboardManager manager = Bukkit.getScoreboardManager();
                            Scoreboard scoreboard = manager.getNewScoreboard();
                            Objective obj = scoreboard.registerNewObjective("4v4", "dummy");

                            obj.setDisplayName(t(scoreboard_title));

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

                            if (gm.getGameOfPlayer(player).getTeam("红之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                redTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("红之队").getPlayers().size() == 0) {
                                redTeamStat = meaning_team_none;
                            } else {
                                redTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("红之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("黄之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                yellowTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("黄之队").getPlayers().size() == 0) {
                                yellowTeamStat = meaning_team_none;
                            } else {
                                yellowTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("黄之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("绿之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                greenTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("绿之队").getPlayers().size() == 0) {
                                greenTeamStat = meaning_team_none;
                            } else {
                                greenTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("绿之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("蓝之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                blueTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("蓝之队").getPlayers().size() == 0) {
                                blueTeamStat = meaning_team_none;
                            } else {
                                blueTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("蓝之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("青之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                aquaTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("青之队").getPlayers().size() == 0) {
                                aquaTeamStat = meaning_team_none;
                            } else {
                                aquaTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("青之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("白之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                whiteTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("白之队").getPlayers().size() == 0) {
                                whiteTeamStat = meaning_team_none;
                            } else {
                                whiteTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("白之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("粉之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                pinkTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("粉之队").getPlayers().size() == 0) {
                                pinkTeamStat = meaning_team_none;
                            } else {
                                pinkTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("粉之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getTeam("灰之队").getTargetHeadBlock().getBlock().getType() == Material.BED_BLOCK) {
                                grayTeamStat = meaning_teambed_yes;
                            } else if (gm.getGameOfPlayer(player).getTeam("灰之队").getPlayers().size() == 0) {
                                grayTeamStat = meaning_team_none;
                            } else {
                                grayTeamStat = meaning_teambed_no.replace("{aliveCount}",gm.getGameOfPlayer(player).getTeam("灰之队").getPlayers().size()+"");
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("RED")){
                                redTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                redTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("YELLOW")){
                                yellowTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                yellowTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("GREEN")){
                                greenTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                greenTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("BLUE")){
                                blueTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                blueTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("AQUA")){
                                aquaTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                aquaTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("WHITE")){
                                whiteTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                whiteTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("PINK")){
                                pinkTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                pinkTeamIsMe = meaning_NotYou;
                            }

                            if (gm.getGameOfPlayer(player).getPlayerTeam(player).getColor().name().equals("GRAY")){
                                grayTeamIsMe = meaning_You.replace("{teamColor}",gm.getGameOfPlayer(player).getPlayerTeam(player).getChatColor().toString());
                            }else {
                                grayTeamIsMe = meaning_NotYou;
                            }



                            String emptya = t(" ");
                            String gameTask = t("&a" + gameCD + "&f秒之后" + "" + "&b游戏结束");
                            String emptyb = t("  ");
                            String redTeam = t( redTeamIsMe + " &c" + "红之队 " + redTeamStat);
                            String yellowTeam = t(yellowTeamIsMe + " &e" + "黄之队 " + yellowTeamStat);
                            String greenTeam = t(greenTeamIsMe + " &a" + "绿之队 " + greenTeamStat);
                            String blueTeam = t(blueTeamIsMe + " &9" + "蓝之队 " + blueTeamStat);
                            String emptyc = t(aquaTeamIsMe + " &b" + "青之队 " + aquaTeamStat);
                            String kill = t(whiteTeamIsMe + " &f" + "白之队 " + whiteTeamStat);
                            String breakbed = t(pinkTeamIsMe + " &d" + "粉之队 " + pinkTeamStat);
                            String finalkill = t(grayTeamIsMe + " &7" + "灰之队 " + grayTeamStat);
                            String emptyd = t("   ");
                            String ip = t("&emc.bedwars.org");

                            obj.getScore(emptya).setScore(12);
                            obj.getScore(gameTask).setScore(11);
                            obj.getScore(emptyb).setScore(10);
                            obj.getScore(redTeam).setScore(9);
                            obj.getScore(yellowTeam).setScore(8);
                            obj.getScore(greenTeam).setScore(7);
                            obj.getScore(blueTeam).setScore(6);
                            obj.getScore(emptyc).setScore(5);
                            obj.getScore(kill).setScore(4);
                            obj.getScore(breakbed).setScore(3);
                            obj.getScore(finalkill).setScore(2);
                            obj.getScore(emptyd).setScore(1);
                            obj.getScore(ip).setScore(0);

                        }

                        if (player.getWorld().getName().contains("rush") && BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player).getTimeLeft() == 3600){
                            cancel();
                            player.setScoreboard(BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player).getScoreboard());
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

            switch (playerteam) {
                case "RED":
                    mao_meta.setColor(Color.fromRGB(255,0,0));
                    yi_meta.setColor(Color.fromRGB(255,0,0));
                    ku_meta.setColor(Color.fromRGB(255,0,0));
                    xie_meta.setColor(Color.fromRGB(255,0,0));
                    break;
                case "BLUE":
                    mao_meta.setColor(Color.fromRGB(0,0,255));
                    yi_meta.setColor(Color.fromRGB(0,0,255));
                    ku_meta.setColor(Color.fromRGB(0,0,255));
                    xie_meta.setColor(Color.fromRGB(0,0,255));
                    break;
                case "GREEN":
                    mao_meta.setColor(Color.fromRGB(0,255,0));
                    yi_meta.setColor(Color.fromRGB(0,255,0));
                    ku_meta.setColor(Color.fromRGB(0,255,0));
                    xie_meta.setColor(Color.fromRGB(0,255,0));
                    break;
                case "YELLOW":
                    mao_meta.setColor(Color.fromRGB(255,255,0));
                    yi_meta.setColor(Color.fromRGB(255,255,0));
                    ku_meta.setColor(Color.fromRGB(255,255,0));
                    xie_meta.setColor(Color.fromRGB(255,255,0));
                    break;
                case "WHITE":
                    mao_meta.setColor(Color.fromRGB(255,255,255));
                    yi_meta.setColor(Color.fromRGB(255,255,255));
                    ku_meta.setColor(Color.fromRGB(255,255,255));
                    xie_meta.setColor(Color.fromRGB(255,255,255));
                    break;
                case "AQUA":
                    mao_meta.setColor(Color.fromRGB(0,255,255));
                    yi_meta.setColor(Color.fromRGB(0,255,255));
                    ku_meta.setColor(Color.fromRGB(0,255,255));
                    xie_meta.setColor(Color.fromRGB(0,255,255));
                    break;
                case "LIGHT_PURPLE":
                    mao_meta.setColor(Color.fromRGB(255,105,180));
                    yi_meta.setColor(Color.fromRGB(255,105,180));
                    ku_meta.setColor(Color.fromRGB(255,105,180));
                    xie_meta.setColor(Color.fromRGB(255,105,180));
                    break;
                case "GRAY":
                    mao_meta.setColor(Color.fromRGB(190,190,190));
                    yi_meta.setColor(Color.fromRGB(190,190,190));
                    ku_meta.setColor(Color.fromRGB(190,190,190));
                    xie_meta.setColor(Color.fromRGB(190,190,190));
                    break;
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

    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (death_gamemode){
            Plugin plugin = this;
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
                            mao_meta.setColor(Color.fromRGB(255,0,0));
                            yi_meta.setColor(Color.fromRGB(255,0,0));
                            ku_meta.setColor(Color.fromRGB(255,0,0));
                            xie_meta.setColor(Color.fromRGB(255,0,0));
                            break;
                        case "BLUE":
                            mao_meta.setColor(Color.fromRGB(0,0,255));
                            yi_meta.setColor(Color.fromRGB(0,0,255));
                            ku_meta.setColor(Color.fromRGB(0,0,255));
                            xie_meta.setColor(Color.fromRGB(0,0,255));
                            break;
                        case "GREEN":
                            mao_meta.setColor(Color.fromRGB(0,255,0));
                            yi_meta.setColor(Color.fromRGB(0,255,0));
                            ku_meta.setColor(Color.fromRGB(0,255,0));
                            xie_meta.setColor(Color.fromRGB(0,255,0));
                            break;
                        case "YELLOW":
                            mao_meta.setColor(Color.fromRGB(255,255,0));
                            yi_meta.setColor(Color.fromRGB(255,255,0));
                            ku_meta.setColor(Color.fromRGB(255,255,0));
                            xie_meta.setColor(Color.fromRGB(255,255,0));
                            break;
                        case "WHITE":
                            mao_meta.setColor(Color.fromRGB(255,255,255));
                            yi_meta.setColor(Color.fromRGB(255,255,255));
                            ku_meta.setColor(Color.fromRGB(255,255,255));
                            xie_meta.setColor(Color.fromRGB(255,255,255));
                            break;
                        case "AQUA":
                            mao_meta.setColor(Color.fromRGB(0,255,255));
                            yi_meta.setColor(Color.fromRGB(0,255,255));
                            ku_meta.setColor(Color.fromRGB(0,255,255));
                            xie_meta.setColor(Color.fromRGB(0,255,255));
                            break;
                        case "LIGHT_PURPLE":
                            mao_meta.setColor(Color.fromRGB(255,105,180));
                            yi_meta.setColor(Color.fromRGB(255,105,180));
                            ku_meta.setColor(Color.fromRGB(255,105,180));
                            xie_meta.setColor(Color.fromRGB(255,105,180));
                            break;
                        case "GRAY":
                            mao_meta.setColor(Color.fromRGB(190,190,190));
                            yi_meta.setColor(Color.fromRGB(190,190,190));
                            ku_meta.setColor(Color.fromRGB(190,190,190));
                            xie_meta.setColor(Color.fromRGB(190,190,190));
                            break;
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
                    //End
                }
            }.

                    runTaskLater(plugin,2L);
        }

    }
        @EventHandler
        public void on (PlayerMoveEvent e){
            Player p = e.getPlayer();
            String playerName = p.getPlayer().getName();
            Inventory inv = p.getInventory();
            Plugin plugin = this;
            new BukkitRunnable() {
                public void run() {

                    if (p.getWorld().getName().contains(rushWorld) && nohunger && p.getFoodLevel() != 20) {
                        p.setFoodLevel(20);
                    }
                    if (p.getWorld().getName().contains(rushWorld) && BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getTimeLeft() < BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).getLength()) {
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
                            if (!ArmorIron.contains(playerName) && !ArmorDiamond.contains(playerName)){
                            ArmorChain.add(playerName);
                                inv.remove(Material.CHAINMAIL_CHESTPLATE);
                                p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                                p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                            }
                        }
                        if (inv.contains(Material.IRON_CHESTPLATE)) {
                            if (!ArmorDiamond.contains(playerName)){
                                ArmorDiamond.add(playerName);
                                inv.remove(Material.IRON_CHESTPLATE);
                                p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                            }
                        }
                        if (inv.contains(Material.DIAMOND_CHESTPLATE)) {
                            ArmorDiamond.add(playerName);
                            inv.remove(Material.DIAMOND_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                        }

                    }
                    cancel();
                }
                //End
            }.runTaskLater(plugin, 5L);
        }
        @EventHandler
    public void on(BedwarsTargetBlockDestroyedEvent e) {

        Player breakPlayer = e.getPlayer();

        String breakPlayerName = e.getPlayer().getName();
        String breakPlayerTeamName = e.getGame().getPlayerTeam(breakPlayer).getName();

        String breakTeamName = e.getTeam().getName();
        String breakTeamColor = e.getTeam().getChatColor().toString();

        String breakPlayerTeamColor = e.getGame().getPlayerTeam(breakPlayer).getChatColor().toString();

        String break_title_breakTeam_real = break_title_breakTeam.replace("{BreakTeamColor}",breakTeamColor).replace("{BreakTeamName}",breakTeamName).replace("{BreakPlayerTeamColor}",breakPlayerTeamColor).replace("{BreakPlayerName}",breakPlayerName).replace("{breakPlayerTeamName}",breakPlayerTeamName);
        String break_subtitle_breakTeam_real = break_subtitle_breakTeam.replace("{BreakTeamColor}",breakTeamColor).replace("{BreakTeamName}",breakTeamName).replace("{BreakPlayerTeamColor}",breakPlayerTeamColor).replace("{BreakPlayerName}",breakPlayerName).replace("{breakPlayerTeamName}",breakPlayerTeamName);
        String break_title_breakPlayer_real = break_title_breakPlayer.replace("{BreakTeamColor}",breakTeamColor).replace("{BreakTeamName}",breakTeamName).replace("{BreakPlayerTeamColor}",breakPlayerTeamColor).replace("{BreakPlayerName}",breakPlayerName).replace("{breakPlayerTeamName}",breakPlayerTeamName);
        String break_subtitle_breakPlayer_real = break_subtitle_breakPlayer.replace("{BreakTeamColor}",breakTeamColor).replace("{BreakTeamName}",breakTeamName).replace("{BreakPlayerTeamColor}",breakPlayerTeamColor).replace("{BreakPlayerName}",breakPlayerName).replace("{breakPlayerTeamName}",breakPlayerTeamName);
        String break_title_all_real = break_title_all.replace("{BreakTeamColor}",breakTeamColor).replace("{BreakTeamName}",breakTeamName).replace("{BreakPlayerTeamColor}",breakPlayerTeamColor).replace("{BreakPlayerName}",breakPlayerName).replace("{breakPlayerTeamName}",breakPlayerTeamName);
        String break_subtitle_all_real = break_subtitle_all.replace("{BreakTeamColor}",breakTeamColor).replace("{BreakTeamName}",breakTeamName).replace("{BreakPlayerTeamColor}",breakPlayerTeamColor).replace("{BreakPlayerName}",breakPlayerName).replace("{breakPlayerTeamName}",breakPlayerTeamName);
        Plugin plugin = this;
        new BukkitRunnable() {
            public void run() {
                if (breaktitle) {
                    breakPlayer.sendTitle(t(break_title_breakPlayer_real) , t(break_subtitle_breakPlayer_real));
                    e.getGame().getPlayers().forEach((player) -> {
                        String playerTeam = e.getGame().getPlayerTeam(player).getName();
                        if (!Objects.equals(player.getName(), breakPlayerName) && !Objects.equals(playerTeam, breakTeamName)){
                            player.sendTitle(t(break_title_all_real) , t(break_subtitle_all_real));
                        }else if (Objects.equals(playerTeam, breakTeamName)){
                            player.sendTitle(t(break_title_breakTeam_real),t(break_subtitle_breakTeam_real));
                        }
                    });
                }
                cancel();
            }
            //End
        }.runTaskLater(plugin,1L);
    }
    @EventHandler
    public void on(BlockBreakEvent e) {
        // Vector te = p.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().setY(0.3).multiply(2);
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        Location tele = p.getLocation().add(0.0, tp_dis, 0.0);
        loc.setY(loc.getY() - 0.07);
        Material block = loc.getWorld().getBlockAt(loc).getType();
        if (break_bed_check){
                Material breakblock = e.getBlock().getType();
                if (block == Material.BED_BLOCK && breakblock == Material.BED_BLOCK) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.teleport(tele);
                    p.setFallDistance(0.0f);
                }
            }
        }
    @EventHandler
    public void on(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();

        if (p.getWorld().getName().contains(rushWorld)) {
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

    @EventHandler
    public void on(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        if(p.getWorld().getName().contains(rushWorld) && no_pearl_damage && e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            e.setCancelled(true);
            p.teleport(e.getTo());
            p.setFallDistance(0.0f);
        }
    }

    @EventHandler
    public void on(BedwarsPlayerJoinedEvent e){
        Player p = e.getPlayer();
        p.setFallDistance(256.0f);

    }
    @EventHandler
    public void on(PlayerJoinArenaEvent e){
        Player p = e.getPlayer();
        p.setFallDistance(256.0f);
    }

        //End
    }

