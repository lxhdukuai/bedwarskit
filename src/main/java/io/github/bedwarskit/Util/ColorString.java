package io.github.bedwarskit.Util;

import org.bukkit.ChatColor;

public class ColorString {
    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
