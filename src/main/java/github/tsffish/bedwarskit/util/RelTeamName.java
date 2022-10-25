package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.MainConfigHandler;

/**
 * @author tsffish
 * @see RelArmorColor
 */
public class RelTeamName {
    public static String RED_TEAM_NAME = "红之队";
    public static String YELLOW_TEAM_NAME = "黄之队";
    public static String GREEN_TEAM_NAME = "绿之队";
    public static String BLUE_TEAM_NAME = "蓝之队";
    public static String AQUA_TEAM_NAME = "青之队";
    public static String WHITE_TEAM_NAME = "白之队";
    public static String PINK_TEAM_NAME = "粉之队";
    public static String GRAY_TEAM_NAME = "灰之队";
    public static void loadRelTeamName(){
        RED_TEAM_NAME = MainConfigHandler.relTeamName_Red;
        YELLOW_TEAM_NAME = MainConfigHandler.relTeamName_Yellow;
        GREEN_TEAM_NAME = MainConfigHandler.relTeamName_Green;
        BLUE_TEAM_NAME = MainConfigHandler.relTeamName_Blue;
        AQUA_TEAM_NAME = MainConfigHandler.relTeamName_Aqua;
        PINK_TEAM_NAME = MainConfigHandler.relTeamName_Pink;
        GRAY_TEAM_NAME = MainConfigHandler.relTeamName_Gray;
        WHITE_TEAM_NAME = MainConfigHandler.relTeamName_White;
    }
}
