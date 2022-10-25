package github.tsffish.bedwarskit.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

import static github.tsffish.bedwarskit.Main.c;
import static github.tsffish.bedwarskit.util.ConfigErrorHandler.cr;

public class RelConfigHandler {
    public static Logger l = Bukkit.getLogger();
    // 再此声明所有变量类型
    public static String bwrelPrefix;
    public static String bwrelPrefixPath = "chat-prefix";
    //加载主配置文件
    public static void loadRelConfig() {
        FileConfiguration BWREL_CONFIG = Bukkit.getPluginManager().getPlugin("BedwarsRel").getConfig();
        if (BWREL_CONFIG.contains(bwrelPrefixPath)) {
            bwrelPrefix = BWREL_CONFIG.getString(bwrelPrefixPath);
        }else {
            l.info(cr(c + "BedwarsRel", bwrelPrefixPath));
        }
    }
}
