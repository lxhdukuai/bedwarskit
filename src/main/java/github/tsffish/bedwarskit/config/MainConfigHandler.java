package github.tsffish.bedwarskit.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static github.tsffish.bedwarskit.Main.c;
import static github.tsffish.bedwarskit.util.ConfigErrorHandler.cr;

/**
 * @author tsffish
 */
public class MainConfigHandler {
    // 配置文件定位
    // public static final File LANG_CONFIG = new File("plugins/bedwarskit/config.yml");
    public static Logger l = Bukkit.getLogger();
    // 再此声明所有变量类型
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
    public static String serverIp;
    public static String serverIpPath = "server_ip";
    public static String relTeamName_Red;
    public static String relTeamName_Red_Path = "rel_team_name.Red";
    public static String relTeamName_Blue;
    public static String relTeamName_Blue_Path = "rel_team_name.Blue";
    public static String relTeamName_Green;
    public static String relTeamName_Green_Path = "rel_team_name.Green";
    public static String relTeamName_Yellow;
    public static String relTeamName_Yellow_Path = "rel_team_name.Yellow";
    public static String relTeamName_Aqua;
    public static String relTeamName_Aqua_Path = "rel_team_name.Aqua";
    public static String relTeamName_White;
    public static String relTeamName_White_Path = "rel_team_name.White";
    public static String relTeamName_Gray;
    public static String relTeamName_Gray_Path = "rel_team_name.Gray";
    public static String relTeamName_Pink;
    public static String relTeamName_Pink_Path = "rel_team_name.Pink";
    public static boolean rushModeSpeed;
    public static String rushModeSpeedPath = "rushmode_speed.enable";
    public static int rushModeSpeedLevel;
    public static String rushModeSpeedLevelPath = "rushmode_speed.level";
    public static String relTeamColorName_Red;
    public static String relTeamColorName_Red_Path = "rel_teamcolor_name.Red";
    public static String relTeamColorName_Blue;
    public static String relTeamColorName_Blue_Path = "rel_teamcolor_name.Blue";
    public static String relTeamColorName_Green;
    public static String relTeamColorName_Green_Path = "rel_teamcolor_name.Green";
    public static String relTeamColorName_Yellow;
    public static String relTeamColorName_Yellow_Path = "rel_teamcolor_name.Yellow";
    public static String relTeamColorName_Aqua;
    public static String relTeamColorName_Aqua_Path = "rel_teamcolor_name.Aqua";
    public static String relTeamColorName_White;
    public static String relTeamColorName_White_Path = "rel_teamcolor_name.White";
    public static String relTeamColorName_Gray;
    public static String relTeamColorName_Gray_Path = "rel_teamcolor_name.Gray";
    public static String relTeamColorName_Pink;
    public static String relTeamColorName_Pink_Path = "rel_teamcolor_name.Pink";
    public static List<String> NoDropList = new ArrayList<>();
    public static String NoDropListPath = "nodroplist";
    public static String langReloaded;
    public static String langReloadedPath = "lang.reloaded";
    //加载主配置文件
    public static void loadMainConfig() {
        Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();
        FileConfiguration MAIN_CONFIG = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();
        Bukkit.getPluginManager().getPlugin("BedwarsKit").reloadConfig();
        if (MAIN_CONFIG.contains(langReloadedPath)){
            langReloaded = MAIN_CONFIG.getString(langReloadedPath);
        }else {
            l.info(cr(c + "BedwarsKit", langReloadedPath));
        }
        if (MAIN_CONFIG.contains(NoDropListPath)){
            NoDropList = MAIN_CONFIG.getStringList(NoDropListPath);
        }else {
            l.info(cr(c + "BedwarsKit", NoDropListPath));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Red_Path)) {
            relTeamColorName_Red = MAIN_CONFIG.getString(relTeamColorName_Red_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Red_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Blue_Path)) {
            relTeamColorName_Blue = MAIN_CONFIG.getString(relTeamColorName_Blue_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Blue_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Green_Path)) {
            relTeamColorName_Green = MAIN_CONFIG.getString(relTeamColorName_Green_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Green_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Yellow_Path)) {
            relTeamColorName_Yellow = MAIN_CONFIG.getString(relTeamColorName_Yellow_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Yellow_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Aqua_Path)) {
            relTeamColorName_Aqua = MAIN_CONFIG.getString(relTeamColorName_Aqua_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Aqua_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_White_Path)) {
            relTeamColorName_White = MAIN_CONFIG.getString(relTeamColorName_White_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_White_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Gray_Path)) {
            relTeamColorName_Gray = MAIN_CONFIG.getString(relTeamColorName_Gray_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Gray_Path));
        }
        if (MAIN_CONFIG.contains(relTeamColorName_Pink_Path)) {
            relTeamColorName_Pink = MAIN_CONFIG.getString(relTeamColorName_Pink_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamColorName_Pink_Path));
        }
        if (MAIN_CONFIG.contains(rushModeSpeedLevelPath)) {
            rushModeSpeedLevel = MAIN_CONFIG.getInt(rushModeSpeedLevelPath);
        }else {
            l.info(cr(c + "BedwarsKit", rushModeSpeedLevelPath));
        }
        if (MAIN_CONFIG.contains(rushModeSpeedPath)) {
            rushModeSpeed = MAIN_CONFIG.getBoolean(rushModeSpeedPath);
        }else {
            l.info(cr(c + "BedwarsKit", rushModeSpeedPath));
        }
        if (MAIN_CONFIG.contains(breakTitlePath)) {
            breakTitle = MAIN_CONFIG.getBoolean(breakTitlePath);
        }else {
            l.info(cr(c + "BedwarsKit", breakTitlePath));
        }
        if (MAIN_CONFIG.contains(breakTitleAllPath)) {
            breakTitleAll = MAIN_CONFIG.getString(breakTitleAllPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakTitleAllPath));
        }
        if (MAIN_CONFIG.contains(breakSubTitleAllPath)) {
            breakSubTitleAll = MAIN_CONFIG.getString(breakSubTitleAllPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakSubTitleAllPath));
        }
        if (MAIN_CONFIG.contains(breakTitleBreakPlayerPath)) {
            breakTitleBreakPlayer = MAIN_CONFIG.getString(breakTitleBreakPlayerPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakTitleBreakPlayerPath));
        }
        if (MAIN_CONFIG.contains(breakSubTitleBreakPlayerPath)) {
            breakSubTitleBreakPlayer = MAIN_CONFIG.getString(breakSubTitleBreakPlayerPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakSubTitleBreakPlayerPath));
        }
        if (MAIN_CONFIG.contains(breakTitleBreakTeamPath)) {
            breakTitleBreakTeam = MAIN_CONFIG.getString(breakTitleBreakTeamPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakTitleBreakTeamPath));
        }
        if (MAIN_CONFIG.contains(breakSubTitleBreakTeamPath)) {
            breakSubTitleBreakTeam = MAIN_CONFIG.getString(breakSubTitleBreakTeamPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakSubTitleBreakTeamPath));
        }
        if (MAIN_CONFIG.contains(noHungerPath)) {
            noHunger = MAIN_CONFIG.getBoolean(noHungerPath);
        }else {
            l.info(cr(c + "BedwarsKit", noHungerPath));
        }
        if (MAIN_CONFIG.contains(maxFoodLevelPath)) {
            maxFoodLevel = MAIN_CONFIG.getInt(maxFoodLevelPath);
        }else {
            l.info(cr(c + "BedwarsKit", maxFoodLevelPath));
        }
        if (MAIN_CONFIG.contains(noPearlDamagePath)) {
            noPearlDamage = MAIN_CONFIG.getBoolean(noPearlDamagePath);
        }else {
            l.info(cr(c + "BedwarsKit", noPearlDamagePath));
        }
        if (MAIN_CONFIG.contains(breakBedCheckPath)) {
            breakBedCheck = MAIN_CONFIG.getBoolean(breakBedCheckPath);
        }else {
            l.info(cr(c + "BedwarsKit", breakBedCheckPath));
        }
        if (MAIN_CONFIG.contains(tpDisPath)) {
            tpDis = MAIN_CONFIG.getDouble(tpDisPath);
        }else {
            l.info(cr(c + "BedwarsKit", tpDisPath));
        }
        if (MAIN_CONFIG.contains(deathGameModePath)) {
            deathGameMode = MAIN_CONFIG.getBoolean(deathGameModePath);
        }else {
            l.info(cr(c + "BedwarsKit", deathGameModePath));
        }
        if (MAIN_CONFIG.contains(killEffectPath)) {
            killEffect = MAIN_CONFIG.getBoolean(killEffectPath);
        }else {
            l.info(cr(c + "BedwarsKit", killEffectPath));
        }
        if (MAIN_CONFIG.contains(rushWorldPath)) {
            rushWorld = MAIN_CONFIG.getString(rushWorldPath);
        }else {
            l.info(cr(c + "BedwarsKit", rushWorldPath));
        }
        if (MAIN_CONFIG.contains(rushWorld2v2Path)) {
            rushWorld2v2 = MAIN_CONFIG.getString(rushWorld2v2Path);
        }else {
            l.info(cr(c + "BedwarsKit", rushWorld2v2Path));
        }
        if (MAIN_CONFIG.contains(rushWorld4v4Path)) {
            rushWorld4v4 = MAIN_CONFIG.getString(rushWorld4v4Path);
        }else {
            l.info(cr(c + "BedwarsKit", rushWorld4v4Path));
        }
        if (MAIN_CONFIG.contains(lobbyWorldPath)) {
            lobbyWorld = MAIN_CONFIG.getString(lobbyWorldPath);
        }else {
            l.info(cr(c + "BedwarsKit", lobbyWorldPath));
        }
        if (MAIN_CONFIG.contains(startKitCompassPath)) {
            startKitCompass = MAIN_CONFIG.getBoolean(startKitCompassPath);
        }else {
            l.info(cr(c + "BedwarsKit", startKitCompassPath));
        }
        if (MAIN_CONFIG.contains(respawnDelayPath)) {
            respawnDelay = MAIN_CONFIG.getInt(respawnDelayPath);
        }else {
            l.info(cr(c + "BedwarsKit", respawnDelayPath));
        }
        if (MAIN_CONFIG.contains(respawnTitlePath)) {
            respawnTitle = MAIN_CONFIG.getString(respawnTitlePath);
        }else {
            l.info(cr(c + "BedwarsKit", respawnTitlePath));
        }
        if (MAIN_CONFIG.contains(respawnSubTitlePath)) {
            respawnSubTitle = MAIN_CONFIG.getString(respawnSubTitlePath);
        }else {
            l.info(cr(c + "BedwarsKit", respawnSubTitlePath));
        }
        if (MAIN_CONFIG.contains(respawnSuccTitlePath)) {
            respawnSuccTitle = MAIN_CONFIG.getString(respawnSuccTitlePath);
        }else {
            l.info(cr(c + "BedwarsKit", respawnSuccTitlePath));
        }
        if (MAIN_CONFIG.contains(respawnSuccSubTitlePath)) {
            respawnSuccSubTitle = MAIN_CONFIG.getString(respawnSuccSubTitlePath);
        }else {
            l.info(cr(c + "BedwarsKit", respawnSuccSubTitlePath));
        }
        if (MAIN_CONFIG.contains(customScoreboardPath)) {
            customScoreboard = MAIN_CONFIG.getBoolean(customScoreboardPath);
        }else {
            l.info(cr(c + "BedwarsKit", customScoreboardPath));
        }
        if (MAIN_CONFIG.contains(scoreboardTitlePath)) {
            scoreboardTitle = MAIN_CONFIG.getString(scoreboardTitlePath);
        }else {
            l.info(cr(c + "BedwarsKit", scoreboardTitlePath));
        }
        if (MAIN_CONFIG.contains(antiDropPath)) {
            antiDrop = MAIN_CONFIG.getBoolean(antiDropPath);
        }else {
            l.info(cr(c + "BedwarsKit", antiDropPath));
        }
        if (MAIN_CONFIG.contains(switchworldFallPath)) {
            switchworldFall = MAIN_CONFIG.getBoolean(switchworldFallPath);
        }else {
            l.info(cr(c + "BedwarsKit", switchworldFallPath));
        }
        if (MAIN_CONFIG.contains(meanTeamBedYesPath)) {
            meanTeamBedYes = MAIN_CONFIG.getString(meanTeamBedYesPath);
        }else {
            l.info(cr(c + "BedwarsKit", meanTeamBedYesPath));
        }
        if (MAIN_CONFIG.contains(meanTeamBedNoPath)) {
            meanTeamBedNo = MAIN_CONFIG.getString(meanTeamBedNoPath);
        }else {
            l.info(cr(c + "BedwarsKit", meanTeamBedNoPath));
        }
        if (MAIN_CONFIG.contains(meanTeamNonePath)) {
            meanTeamNone = MAIN_CONFIG.getString(meanTeamNonePath);
        }else {
            l.info(cr(c + "BedwarsKit", meanTeamNonePath));
        }
        if (MAIN_CONFIG.contains(serverIpPath)) {
            serverIp = MAIN_CONFIG.getString(serverIpPath);
        }else {
            l.info(cr(c + "BedwarsKit", serverIpPath));
        }
        if (MAIN_CONFIG.contains(relTeamName_Red_Path)) {
            relTeamName_Red = MAIN_CONFIG.getString(relTeamName_Red_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Red_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_Blue_Path)) {
            relTeamName_Blue = MAIN_CONFIG.getString(relTeamName_Blue_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Blue_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_Green_Path)) {
            relTeamName_Green = MAIN_CONFIG.getString(relTeamName_Green_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Green_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_Yellow_Path)) {
            relTeamName_Yellow = MAIN_CONFIG.getString(relTeamName_Yellow_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Yellow_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_Aqua_Path)) {
            relTeamName_Aqua = MAIN_CONFIG.getString(relTeamName_Aqua_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Aqua_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_Gray_Path)) {
            relTeamName_Gray = MAIN_CONFIG.getString(relTeamName_Gray_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Gray_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_Pink_Path)) {
            relTeamName_Pink = MAIN_CONFIG.getString(relTeamName_Pink_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_Pink_Path));
        }
        if (MAIN_CONFIG.contains(relTeamName_White_Path)) {
            relTeamName_White = MAIN_CONFIG.getString(relTeamName_White_Path);
        }else {
            l.info(cr(c + "BedwarsKit", relTeamName_White_Path));
        }
        if (MAIN_CONFIG.contains(meanYouPath)) {
            meanYou = MAIN_CONFIG.getString(meanYouPath);
        }else {
            l.info(cr(c + "BedwarsKit", meanYouPath));
        }
        if (MAIN_CONFIG.contains(meanNotYouPath)) {
            meanNotYou = MAIN_CONFIG.getString(meanNotYouPath);
        }else {
            l.info(cr(c + "BedwarsKit", meanNotYouPath));
        }
    }
}
