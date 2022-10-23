package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.MainConfigHandler;

/**
 * @author tsffish
 * @see RelArmorColor
 */
public class RelTeamColorName {
    public static String RED_TEAM_COLOR_NAME = "RED";
    public static String BLUE_TEAM_COLOR_NAME = "BLUE";
    public static String GREEN_TEAM_COLOR_NAME = "GREEN";
    public static String YELLOW_TEAM_COLOR_NAME = "YELLOW";
    public static String AQUA_TEAM_COLOR_NAME = "AQUA";
    public static String WHITE_TEAM_COLOR_NAME = "WHITE";
    public static String GRAY_TEAM_COLOR_NAME = "GRAY";
    public static String PINK_TEAM_COLOR_NAME = "LIGHT_PURPLE";
    public static void loadRelTeamColorName(){
        RED_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Red;
        BLUE_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Blue;
        GREEN_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Green;
        YELLOW_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Yellow;
        AQUA_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Aqua;
        WHITE_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_White;
        GRAY_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Gray;
        PINK_TEAM_COLOR_NAME = MainConfigHandler.relTeamColorName_Pink;
    }
}
