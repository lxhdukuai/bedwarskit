package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static github.tsffish.bedwarskit.util.ColorString.t;

/**
 * @author tsffish
 * @see RelBreakCorrect
 */
public class RelBreakBed implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);
    public boolean breakTitle = MainConfigHandler.breakTitle;
    public String breakTitleAll = MainConfigHandler.breakTitleAll;
    public String breakSubTitleAll = MainConfigHandler.breakSubTitleAll;
    public String breakTitleBreakPlayer = MainConfigHandler.breakTitleBreakPlayer;
    public String breakSubTitleBreakPlayer = MainConfigHandler.breakSubTitleBreakPlayer;
    public String breakTitleBreakTeam = MainConfigHandler.breakTitleBreakTeam;
    public String breakSubTitleBreakTeam = MainConfigHandler.breakSubTitleBreakTeam;

    @EventHandler
    public void on(BedwarsTargetBlockDestroyedEvent e) {

        Player breakPlayer = e.getPlayer();
        String playerName = breakPlayer.getName();
        String breakPlayerName = e.getPlayer().getName();
        String breakPlayerTeamName = e.getGame().getPlayerTeam(breakPlayer).getName();

        String breakTeamName = e.getTeam().getName();
        String breakTeamColor = e.getTeam().getChatColor().toString();
        String breakPlayerTeamColor = e.getGame().getPlayerTeam(breakPlayer).getChatColor().toString();

        String breakTitleBreakTeamReal = breakTitleBreakTeam.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String breakSubtitleBreakTeamReal = breakSubTitleBreakTeam.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String breakTitleBreakPlayerReal = breakTitleBreakPlayer.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String breakSubtitleBreakPlayerReal = breakSubTitleBreakPlayer.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String breakTitleAllReal = breakTitleAll.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String breakSubtitleAllReal = breakSubTitleAll.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (breakTitle) {
                    // 向被破坏床的队伍 sendtitle
                    breakPlayer.sendTitle(t(breakTitleBreakTeamReal), t(breakSubtitleBreakTeamReal));
                    e.getGame().getPlayers().forEach((player) -> {
                        String playerTeam = e.getGame().getPlayerTeam(player).getName();
                        // 向被所有的玩家 sendtitle
                        if (!Objects.equals(playerName, breakPlayerName) && !Objects.equals(playerTeam, breakTeamName)) {
                            player.sendTitle(t(breakTitleAllReal), t(breakSubtitleAllReal));
                        } else if (Objects.equals(playerTeam, breakTeamName)) {
                            // 向被破坏床的玩家 sendtitle
                            player.sendTitle(t(breakTitleBreakPlayerReal), t(breakSubtitleBreakPlayerReal));
                        }
                    });
                }
            }
            //End
        }.runTaskLater(plugin, 1L);
    }
}
