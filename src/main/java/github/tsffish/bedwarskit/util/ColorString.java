package github.tsffish.bedwarskit.util;

import org.bukkit.ChatColor;

/**
 * @author tsffish
 */
public class ColorString {
    // 支持彩色字符 &
    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
