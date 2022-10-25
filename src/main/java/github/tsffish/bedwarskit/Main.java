package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.command.bwkitrel;
import github.tsffish.bedwarskit.command.bwkitreload;
import github.tsffish.bedwarskit.listener.bedwars1058.Yi058PlayerRespawn;
import github.tsffish.bedwarskit.listener.bedwarsrel.*;
import github.tsffish.bedwarskit.util.feedback.LobbyPlayerSwitch;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.logging.Logger;

import static github.tsffish.bedwarskit.config.MainConfigHandler.loadMainConfig;
import static github.tsffish.bedwarskit.config.RelConfigHandler.loadRelConfig;
import static github.tsffish.bedwarskit.util.ColorString.t;
import static github.tsffish.bedwarskit.util.RelArmorColor.loadRelArmorColor;
import static github.tsffish.bedwarskit.util.RelNoDropList.loadRelNoDropList;
import static github.tsffish.bedwarskit.util.RelTeamColorName.loadRelTeamColorName;
import static github.tsffish.bedwarskit.util.RelTeamName.loadRelTeamName;
import static github.tsffish.bedwarskit.util.language.thelang.loadLanguage;

/**
 * @author tsffish
 */
public class Main extends JavaPlugin implements Listener {
    private Logger l;
    public String currentLang = Locale.getDefault().toString().toLowerCase();
    public static final String CNLANG = "zh_cn";
    // 控制台消息前缀
    public static String c = t("[BedwarsKit] ");
    @Override
    public void onEnable() {
        l = getLogger();
        //为不同语言显示不同信息
        if (!CNLANG.equals(currentLang)) {
            l.info(c + "正在启用");
        } else {
            l.info(c + "Enable");
        }

        // 加载配置
        loadRelConfig();
        loadMainConfig();
        loadLanguage();
        loadRelArmorColor();
        loadRelTeamName();
        loadRelTeamColorName();
        loadRelNoDropList();

        PluginManager pm = Bukkit.getPluginManager();
        // 注册Event for Bedwars1058
        if (pm.getPlugin("BedWars1058") != null) {
            pm.registerEvents(new Yi058PlayerRespawn(), this);
        }
        // 注册Event for BedwarsRel
        if (pm.getPlugin("BedwarsRel") != null) {
            pm.registerEvents(new RelBreakBed(), this);
            pm.registerEvents(new RelBreakCorrect(), this);
            pm.registerEvents(new RelClickInventory(), this);
            pm.registerEvents(new RelGameStarted(), this);
            pm.registerEvents(new RelPlayerDeath(), this);
            pm.registerEvents(new RelPlayerDrop(), this);
            pm.registerEvents(new RelPlayerMove(), this);
            pm.registerEvents(new RelPlayerRespawn(), this);
            pm.registerEvents(new RelPlayerTeleport(), this);
            pm.registerEvents(new RelKillEffect(), this);
        }
        // 注册Event for All
        pm.registerEvents(new LobbyPlayerSwitch(), this);

        // 注册Command
        getCommand("bwkitreload").setExecutor(new bwkitreload());
        getCommand("bwkitrel").setExecutor(new bwkitrel());
    }

    @Override
    public void onDisable() {
        // 为不同语言显示不同信息
        if (!CNLANG.equals(currentLang)) {
            l.info(c + "正在卸载");
        } else {
            l.info(c + "Disable");
        }
    }

    //End
}

