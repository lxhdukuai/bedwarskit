package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.bedwars1058.Yi058PlayerRespawn;
import github.tsffish.bedwarskit.bedwarsrel.*;
import github.tsffish.bedwarskit.feedback.LobbyPlayerSwitch;
import github.tsffish.bedwarskit.util.BreakCorrect;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

import static github.tsffish.bedwarskit.util.ColorString.t;
import static github.tsffish.bedwarskit.util.ConfigHandler.loadConfig;

/**
 * @author Administrator
 */
public class Main extends JavaPlugin implements Listener {
    private Logger l;
    public String currentLang = Locale.getDefault().toString().toLowerCase();
    public static final String CNLANG = "zh_cn";
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
        loadConfig();

        pm.registerEvents(new Yi058PlayerRespawn(), this);

        pm.registerEvents(new RelBreak(this), this);
        pm.registerEvents(new BreakCorrect(this), this);
        pm.registerEvents(new RelClickInventory(), this);
        pm.registerEvents(new RelGameStarted(this), this);
        pm.registerEvents(new RelPlayerDeath(this), this);
        pm.registerEvents(new RelPlayerDrop(), this);
        pm.registerEvents(new RelPlayerMove(this), this);
        pm.registerEvents(new RelPlayerRespawn(this), this);
        pm.registerEvents(new RelPlayerTeleport(), this);
        pm.registerEvents(new RelKillEffect(), this);

        pm.registerEvents(new LobbyPlayerSwitch(), this);
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
    //End
}

