package github.tsffish.bedwarskit.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.util.ConfigHandler;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static github.tsffish.bedwarskit.util.ColorString.t;

/**
 * @author Administrator
 */
public class RelBreak implements Listener {
    private final Main plugin;

    public RelBreak(Main plugin) {
        this.plugin = plugin;
    }

    public boolean breakTitle = ConfigHandler.breakTitle;
    public String breakTitleAll = ConfigHandler.breakTitleAll;
    public String breakSubTitleAll = ConfigHandler.breakSubTitleAll;
    public String breakTitleBreakPlayer = ConfigHandler.breakTitleBreakPlayer;
    public String breakSubTitleBreakPlayer = ConfigHandler.breakSubTitleBreakPlayer;
    public String breakTitleBreakTeam = ConfigHandler.breakTitleBreakTeam;
    public String breakSubTitleBreakTeam = ConfigHandler.breakSubTitleBreakTeam;

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
                    breakPlayer.sendTitle(t(breakTitleBreakTeamReal), t(breakSubtitleBreakTeamReal));
                    e.getGame().getPlayers().forEach((player) -> {
                        String playerTeam = e.getGame().getPlayerTeam(player).getName();
                        if (!Objects.equals(playerName, breakPlayerName) && !Objects.equals(playerTeam, breakTeamName)) {
                            player.sendTitle(t(breakTitleAllReal), t(breakSubtitleAllReal));
                        } else if (Objects.equals(playerTeam, breakTeamName)) {
                            player.sendTitle(t(breakTitleBreakPlayerReal), t(breakSubtitleBreakPlayerReal));
                        }
                    });
                }
            }
            //End
        }.runTaskLater(plugin, 1L);
    }
}
