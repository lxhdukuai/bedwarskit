package io.github.bedwarskit;

import io.github.bedwarskit.BedwarsRel.*;
import io.github.bedwarskit.FeedBack.LobbyPlayerSwitch;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

import static io.github.bedwarskit.Util.ColorString.t;

public class Main extends JavaPlugin implements Listener {
    private Logger l;
    public String ctlo = Locale.getDefault().toString().toLowerCase();
    public FileConfiguration config = getConfig();
    public String c = t("[BedwarsKit] ");
    @Override
    public void onEnable() {
        l = getLogger();
        if (!ctlo.equals("zh_cn")) {
            l.warning(c + "正在启用");
        } else {
            l.warning(c + "Enable");
        }
        l.warning(ctlo);
        PluginManager pm = Bukkit.getPluginManager();
        saveDefaultConfig();

        pm.registerEvents(this, this);

        pm.registerEvents(new RelBreak(this), this);
        pm.registerEvents(new RelBreakCheck(this), this);
        pm.registerEvents(new RelClickInventory(this), this);
        pm.registerEvents(new RelGameStarted(this), this);
        pm.registerEvents(new RelPlayerDeath(this), this);
        pm.registerEvents(new RelPlayerDrop(this), this);
        pm.registerEvents(new RelPlayerMove(this), this);
        pm.registerEvents(new RelPlayerRespawn(this), this);
        pm.registerEvents(new RelPlayerTeleport(this), this);

        pm.registerEvents(new LobbyPlayerSwitch(this), this);
    }

    @Override
    public void onDisable() {
        if (!ctlo.equals("zh_cn")) {
            l.warning(c + "正在卸载");
        } else {
            l.warning(c + "Disable");
        }
        l = null;
    }

    public List<String> armorChain = new ArrayList<>();
    public List<String> armorIron = new ArrayList<>();
    public List<String> armorDiamond = new ArrayList<>();
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
    public String rushWorld2v2 = config.getString("rushworld2v2");
    public String rushWorld4v4 = config.getString("rushworld4v4");
    public String lobbyworld = config.getString("lobbyworld");
    public boolean anti_drop = config.getBoolean("anti_drop");
    public Integer respawn_delay = config.getInt("respawn_delay");
    public String relTeamRedName = config.getString("team.RED");
    public String relTeamBlueName = config.getString("team.BLUE");
    public String relTeamGreenName = config.getString("team.GREEN");
    public String relTeamYellowName = config.getString("team.YELLOW");
    public String relTeamAquaName = config.getString("team.AQUA");
    public String relTeamWhiteName = config.getString("team.WHITE");
    public String relTeamGrayName = config.getString("team.GRAY");
    public String relTeamPinkName = config.getString("team.PINK");
    public String line13_2v2 = config.getString("line13_2v2");
    public String line9_4v4 = config.getString("line9_4v4");
    public boolean switchworld_fall = config.getBoolean("switchworld_fall");
        //End
    }
