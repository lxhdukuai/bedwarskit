package github.tsffish.bedwarskit.command;

import github.tsffish.bedwarskit.util.language.thelang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static github.tsffish.bedwarskit.config.MainConfigHandler.loadMainConfig;
import static github.tsffish.bedwarskit.util.ColorString.t;
import static github.tsffish.bedwarskit.util.RelNoDropList.loadRelNoDropList;
import static github.tsffish.bedwarskit.util.RelTeamColorName.loadRelTeamColorName;
import static github.tsffish.bedwarskit.util.RelTeamName.loadRelTeamName;
import static github.tsffish.bedwarskit.util.language.thelang.loadLanguage;

public class bwkitreload implements CommandExecutor {
    String reloaded = thelang.reloaded;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        loadMainConfig();
        loadLanguage();
        loadRelTeamName();
        loadRelTeamColorName();
        loadRelNoDropList();
        sender.sendMessage(t(reloaded));
        return false;
    }
}
