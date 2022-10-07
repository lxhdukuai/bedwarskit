package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.bedwarsrel.*;
import github.tsffish.bedwarskit.feedback.LobbyPlayerSwitch;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

import static github.tsffish.bedwarskit.util.ColorString.t;

/**
 * @author Administrator
 */
public class Main extends JavaPlugin implements Listener {
    private Logger l;
    public String currentLang = Locale.getDefault().toString().toLowerCase();
    public static final String CNLANG = "zh_cn";
    public FileConfiguration config = getConfig();
    public String c = t("[BedwarsKit] ");

    @Override
    public void onEnable() {
        l = getLogger();
        if (!CNLANG.equals(currentLang)) {
            l.warning(c + "正在启用");
        } else {
            l.warning(c + "Enable");
        }
        l.warning(currentLang);
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
        pm.registerEvents(new RelKillEffect(this), this);


        pm.registerEvents(new LobbyPlayerSwitch(this), this);
    }

    @Override
    public void onDisable() {
        if (!CNLANG.equals(currentLang)) {
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
    public String breakTitleAll = config.getString("break_title_all");
    public String breakSubtitleAll = config.getString("break_subtitle_all");
    public String breakTitleBreakPlayer = config.getString("break_title_breakPlayer");
    public String breakSubtitleBreakPlayer = config.getString("break_subtitle_breakPlayer");
    public String breakTitleBreakTeam = config.getString("break_title_breakTeam");
    public String breakSubtitleBreakTeam = config.getString("break_subtitle_breakTeam");
    public boolean nohunger = config.getBoolean("no_hunger");
    public boolean noPearlDamage = config.getBoolean("no_pearl_damage");
    public boolean breakBedCheck = config.getBoolean("break_bed_check");
    public boolean customScoreboard = config.getBoolean("custom_scoreboard");
    public double tpDis = config.getDouble("tp_dis");
    public boolean deathGamemode = config.getBoolean("death_gamemode");
    public boolean startKitCompass = config.getBoolean("startkit_compass");
    public String respawnTitle = config.getString("respawn_title");
    public String respawnSubtitle = config.getString("respawn_subtitle");
    public String respawnSuccTitle = config.getString("respawn_succ_title");
    public String respawnSuccSubtitle = config.getString("respawn_succ_subtitle");
    public String meaningTeambedYes = config.getString("meaning_teambed_yes");
    public String meaningTeamNone = config.getString("meaning_team_none");
    public String meaningTeambedNo = config.getString("meaning_teambed_no");
    public String scoreboardTitle = config.getString("scoreboard_title");
    public String meaningYou = config.getString("meaning_You");
    public String meaningNotYou = config.getString("meaning_NotYou");
    public String rushWorld = config.getString("rushworld");
    public String rushWorld2v2 = config.getString("rushworld2v2");
    public String rushWorld4v4 = config.getString("rushworld4v4");
    public String lobbyworld = config.getString("lobbyworld");
    public boolean antiDrop = config.getBoolean("anti_drop");
    public Integer respawnDelay = config.getInt("respawn_delay");
    public String relTeamRedName = config.getString("team.RED");
    public String relTeamBlueName = config.getString("team.BLUE");
    public String relTeamGreenName = config.getString("team.GREEN");
    public String relTeamYellowName = config.getString("team.YELLOW");
    public String relTeamAquaName = config.getString("team.AQUA");
    public String relTeamWhiteName = config.getString("team.WHITE");
    public String relTeamGrayName = config.getString("team.GRAY");
    public String relTeamPinkName = config.getString("team.PINK");
    public String line132v2 = config.getString("line13_2v2");
    public String line94v4 = config.getString("line9_4v4");
    public boolean switchworldFall = config.getBoolean("switchworld_fall");
    public boolean killEffect = config.getBoolean("kill_effect");
    //End
}

