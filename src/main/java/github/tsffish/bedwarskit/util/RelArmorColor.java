package github.tsffish.bedwarskit.util;

import org.bukkit.Color;

/**
 * @author tsffish
 * @see RelTeamColorName
 */
public class RelArmorColor {
    public static Color red ;
    public static Color blue;
    public static Color green;
    public static Color yellow;
    public static Color aqua;
    public static Color gray;
    public static Color pink;
    public static Color white;
    public static void loadRelArmorColor(){
        red = Color.fromRGB(255, 0, 0);
        blue = Color.fromRGB(0, 0, 255);
        green = Color.fromRGB(0, 255, 0);
        yellow = Color.fromRGB(255, 255, 0);
        aqua = Color.fromRGB(0, 255, 255);
        gray = Color.fromRGB(190, 190, 190);
        pink = Color.fromRGB(255, 105, 180);
        white = Color.fromRGB(255, 255, 255);
    }
}
