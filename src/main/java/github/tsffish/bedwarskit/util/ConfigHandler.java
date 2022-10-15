package github.tsffish.bedwarskit.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * @author Administrator
 */
public class ConfigHandler {
    // public static final File LANG_CONFIG = new File("plugins/bedwarskit/config.yml");
    public static boolean breakTitle;
    public static String breakTitlePath = "break_bed_title";
    public static String breakTitleAll;
    public static String breakTitleAllPath = "break_title_all";
    public static String breakSubTitleAll;
    public static String breakSubTitleAllPath = "break_subtitle_all";
    public static String breakTitleBreakPlayer;
    public static String breakTitleBreakPlayerPath = "break_title_breakPlayer";
    public static String breakSubTitleBreakPlayer;
    public static String breakSubTitleBreakPlayerPath = "break_subtitle_breakPlayer";
    public static String breakTitleBreakTeam;
    public static String breakTitleBreakTeamPath = "break_title_breakTeam";
    public static String breakSubTitleBreakTeam;
    public static String breakSubTitleBreakTeamPath = "break_subtitle_breakTeam";
    public static boolean noHunger;
    public static String noHungerPath = "no_hunger";
    public static Integer maxFoodLevel;
    public static String maxFoodLevelPath = "hunger_level";
    public static boolean noPearlDamage;
    public static String noPearlDamagePath = "no_pearl_damage";
    public static boolean breakBedCheck;
    public static String breakBedCheckPath = "break_bed_check";
    public static double tpDis;
    public static String tpDisPath = "tp_dis";
    public static boolean deathGameMode;
    public static String deathGameModePath = "death_gamemode.enable";
    public static boolean killEffect;
    public static String killEffectPath = "kill_effect";
    public static String rushWorld;
    public static String rushWorldPath = "rushworld";
    public static String rushWorld2v2;
    public static String rushWorld2v2Path = "rushworld2v2";
    public static String rushWorld4v4;
    public static String rushWorld4v4Path = "rushworld4v4";
    public static String lobbyWorld;
    public static String lobbyWorldPath = "lobbyworld";
    public static boolean startKitCompass;
    public static String startKitCompassPath = "startkit_compass";
    public static Integer respawnDelay;
    public static String respawnDelayPath = "death_gamemode.respawn_delay";
    public static String respawnTitle;
    public static String respawnTitlePath = "death_gamemode.respawn_title";
    public static String respawnSubTitle;
    public static String respawnSubTitlePath = "death_gamemode.respawn_subtitle";
    public static String respawnSuccTitle;
    public static String respawnSuccTitlePath = "death_gamemode.respawn_succ_title";
    public static String respawnSuccSubTitle;
    public static String respawnSuccSubTitlePath = "death_gamemode.respawn_succ_subtitle";
    public static boolean customScoreboard;
    public static String customScoreboardPath = "custom_scoreboard";
    public static String scoreboardTitle = "";
    public static String scoreboardTitlePath = "scoreboard_title";
    public static boolean antiDrop;
    public static String antiDropPath = "anti_drop";
    public static boolean switchworldFall;
    public static String switchworldFallPath = "switchworld_fall.enable";
    public static String meanTeamBedYes;
    public static String meanTeamBedYesPath = "mean_teambed_yes";
    public static String meanTeamBedNo;
    public static String meanTeamBedNoPath = "mean_teambed_no";
    public static String meanTeamNone;
    public static String meanTeamNonePath = "mean_team_none";
    public static String meanYou;
    public static String meanYouPath = "mean_You";
    public static String meanNotYou;
    public static String meanNotYouPath = "mean_NotYou";
    public static String bwrelPrefix;
    public static String bwrelPrefixPath = "chat-prefix";
    public static String serverIp;
    public static String serverIpPath = "server_ip";
    public static String relRedTeamName;
    public static String relRedTeamNamePath = "team.RED";
    public static String relBlueTeamName;
    public static String relBlueTeamNamePath = "team.BLUE";
    public static String relGreenTeamName;
    public static String relGreenTeamNamePath = "team.GREEN";
    public static String relYellowTeamName;
    public static String relYellowTeamNamePath = "team.YELLOW";
    public static String relAquaTeamName;
    public static String relAquaTeamNamePath = "team.AQUA";
    public static String relGrayTeamName;
    public static String relGrayTeamNamePath = "team.GRAY";
    public static String relPinkTeamName;
    public static String relPinkTeamNamePath = "team.PINK";
    public static String relWhiteTeamName;
    public static String relWhiteTeamNamePath = "team.WHITE";
    public static boolean rushModeSpeed;
    public static String rushModeSpeedPath = "rushmode_speed.enable";
    public static int rushModeSpeedLevel;
    public static String rushModeSpeedLevelPath = "rushmode_speed.level";
    public static void loadConfig() {
        Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();
        FileConfiguration MAIN_CONFIG = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();
        FileConfiguration BWREL_CONFIG = Bukkit.getPluginManager().getPlugin("BedwarsRel").getConfig();
        Bukkit.getPluginManager().getPlugin("BedwarsKit").reloadConfig();
        if (MAIN_CONFIG.contains(rushModeSpeedLevelPath)) {
            rushModeSpeedLevel = MAIN_CONFIG.getInt(rushModeSpeedLevelPath);
        }
        if (MAIN_CONFIG.contains(rushModeSpeedPath)) {
            rushModeSpeed = MAIN_CONFIG.getBoolean(rushModeSpeedPath);
        }
        if (MAIN_CONFIG.contains(breakTitlePath)) {
            breakTitle = MAIN_CONFIG.getBoolean(breakTitlePath);
        }
        if (MAIN_CONFIG.contains(breakTitleAllPath)) {
            breakTitleAll = MAIN_CONFIG.getString(breakTitleAllPath);
        }
        if (MAIN_CONFIG.contains(breakSubTitleAllPath)) {
            breakSubTitleAll = MAIN_CONFIG.getString(breakSubTitleAllPath);
        }
        if (MAIN_CONFIG.contains(breakTitleBreakPlayerPath)) {
            breakTitleBreakPlayer = MAIN_CONFIG.getString(breakTitleBreakPlayerPath);
        }
        if (MAIN_CONFIG.contains(breakSubTitleBreakPlayerPath)) {
            breakSubTitleBreakPlayer = MAIN_CONFIG.getString(breakSubTitleBreakPlayerPath);
        }
        if (MAIN_CONFIG.contains(breakTitleBreakTeamPath)) {
            breakTitleBreakTeam = MAIN_CONFIG.getString(breakTitleBreakTeamPath);
        }
        if (MAIN_CONFIG.contains(breakSubTitleBreakTeamPath)) {
            breakSubTitleBreakTeam = MAIN_CONFIG.getString(breakSubTitleBreakTeamPath);
        }
        if (MAIN_CONFIG.contains(noHungerPath)) {
            noHunger = MAIN_CONFIG.getBoolean(noHungerPath);
        }
        if (MAIN_CONFIG.contains(maxFoodLevelPath)) {
            maxFoodLevel = MAIN_CONFIG.getInt(maxFoodLevelPath);
        }
        if (MAIN_CONFIG.contains(noPearlDamagePath)) {
            noPearlDamage = MAIN_CONFIG.getBoolean(noPearlDamagePath);
        }
        if (MAIN_CONFIG.contains(breakBedCheckPath)) {
            breakBedCheck = MAIN_CONFIG.getBoolean(breakBedCheckPath);
        }
        if (MAIN_CONFIG.contains(tpDisPath)) {
            tpDis = MAIN_CONFIG.getDouble(tpDisPath);
        }
        if (MAIN_CONFIG.contains(deathGameModePath)) {
            deathGameMode = MAIN_CONFIG.getBoolean(deathGameModePath);
        }
        if (MAIN_CONFIG.contains(killEffectPath)) {
            killEffect = MAIN_CONFIG.getBoolean(killEffectPath);
        }
        if (MAIN_CONFIG.contains(rushWorldPath)) {
            rushWorld = MAIN_CONFIG.getString(rushWorldPath);
        }
        if (MAIN_CONFIG.contains(rushWorld2v2Path)) {
            rushWorld2v2 = MAIN_CONFIG.getString(rushWorld2v2Path);
        }
        if (MAIN_CONFIG.contains(rushWorld4v4Path)) {
            rushWorld4v4 = MAIN_CONFIG.getString(rushWorld4v4Path);
        }
        if (MAIN_CONFIG.contains(lobbyWorldPath)) {
            lobbyWorld = MAIN_CONFIG.getString(lobbyWorldPath);
        }
        if (MAIN_CONFIG.contains(startKitCompassPath)) {
            startKitCompass = MAIN_CONFIG.getBoolean(startKitCompassPath);
        }
        if (MAIN_CONFIG.contains(respawnDelayPath)) {
            respawnDelay = MAIN_CONFIG.getInt(respawnDelayPath);
        }
        if (MAIN_CONFIG.contains(respawnTitlePath)) {
            respawnTitle = MAIN_CONFIG.getString(respawnTitlePath);
        }
        if (MAIN_CONFIG.contains(respawnSubTitlePath)) {
            respawnSubTitle = MAIN_CONFIG.getString(respawnSubTitlePath);
        }
        if (MAIN_CONFIG.contains(respawnSuccTitlePath)) {
            respawnSuccTitle = MAIN_CONFIG.getString(respawnSuccTitlePath);
        }
        if (MAIN_CONFIG.contains(respawnSuccSubTitlePath)) {
            respawnSuccSubTitle = MAIN_CONFIG.getString(respawnSuccSubTitlePath);
        }
        if (MAIN_CONFIG.contains(customScoreboardPath)) {
            customScoreboard = MAIN_CONFIG.getBoolean(customScoreboardPath);
        }
        if (MAIN_CONFIG.contains(scoreboardTitlePath)) {
            scoreboardTitle = MAIN_CONFIG.getString(scoreboardTitlePath);
        }
        if (MAIN_CONFIG.contains(antiDropPath)) {
            antiDrop = MAIN_CONFIG.getBoolean(antiDropPath);
        }
        if (MAIN_CONFIG.contains(switchworldFallPath)) {
            switchworldFall = MAIN_CONFIG.getBoolean(switchworldFallPath);
        }
        if (MAIN_CONFIG.contains(meanTeamBedYesPath)) {
            meanTeamBedYes = MAIN_CONFIG.getString(meanTeamBedYesPath);
        }
        if (MAIN_CONFIG.contains(meanTeamBedNoPath)) {
            meanTeamBedNo = MAIN_CONFIG.getString(meanTeamBedNoPath);
        }
        if (MAIN_CONFIG.contains(meanTeamNonePath)) {
            meanTeamNone = MAIN_CONFIG.getString(meanTeamNonePath);
        }
        if (BWREL_CONFIG.contains(bwrelPrefixPath)) {
            bwrelPrefix = BWREL_CONFIG.getString(bwrelPrefixPath);
        }
        if (MAIN_CONFIG.contains(serverIpPath)) {
            serverIp = MAIN_CONFIG.getString(serverIpPath);
        }
        if (MAIN_CONFIG.contains(relRedTeamNamePath)) {
            relRedTeamName = MAIN_CONFIG.getString(relRedTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relBlueTeamNamePath)) {
            relBlueTeamName = MAIN_CONFIG.getString(relBlueTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relGreenTeamNamePath)) {
            relGreenTeamName = MAIN_CONFIG.getString(relGreenTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relYellowTeamNamePath)) {
            relYellowTeamName = MAIN_CONFIG.getString(relYellowTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relAquaTeamNamePath)) {
            relAquaTeamName = MAIN_CONFIG.getString(relAquaTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relGrayTeamNamePath)) {
            relGrayTeamName = MAIN_CONFIG.getString(relGrayTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relPinkTeamNamePath)) {
            relPinkTeamName = MAIN_CONFIG.getString(relPinkTeamNamePath);
        }
        if (MAIN_CONFIG.contains(relWhiteTeamNamePath)) {
            relWhiteTeamName = MAIN_CONFIG.getString(relWhiteTeamNamePath);
        }
        if (MAIN_CONFIG.contains(meanYouPath)) {
            meanYou = MAIN_CONFIG.getString(meanYouPath);
        }
        if (MAIN_CONFIG.contains(meanNotYouPath)) {
            meanNotYou = MAIN_CONFIG.getString(meanNotYouPath);
        }
    }
}
