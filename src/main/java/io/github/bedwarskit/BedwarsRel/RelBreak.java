package io.github.bedwarskit.BedwarsRel;

import io.github.bedwarskit.Main;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static io.github.bedwarskit.Util.ColorString.t;

public class RelBreak implements Listener {

    private final Main plugin;
    public RelBreak(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void on(BedwarsTargetBlockDestroyedEvent e) {
        Player breakPlayer = e.getPlayer();

        String breakPlayerName = e.getPlayer().getName();
        String breakPlayerTeamName = e.getGame().getPlayerTeam(breakPlayer).getName();

        String breakTeamName = e.getTeam().getName();
        String breakTeamColor = e.getTeam().getChatColor().toString();

        String breakPlayerTeamColor = e.getGame().getPlayerTeam(breakPlayer).getChatColor().toString();

        String break_title_breakTeam_real = plugin.break_title_breakTeam.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String break_subtitle_breakTeam_real = plugin.break_subtitle_breakTeam.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String break_title_breakPlayer_real = plugin.break_title_breakPlayer.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String break_subtitle_breakPlayer_real = plugin.break_subtitle_breakPlayer.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String break_title_all_real = plugin.break_title_all.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        String break_subtitle_all_real = plugin.break_subtitle_all.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
        new BukkitRunnable() {
            public void run() {
                if (plugin.breaktitle) {
                    breakPlayer.sendTitle(t(break_title_breakPlayer_real), t(break_subtitle_breakPlayer_real));
                    e.getGame().getPlayers().forEach((player) -> {
                        String playerTeam = e.getGame().getPlayerTeam(player).getName();
                        if (!Objects.equals(player.getName(), breakPlayerName) && !Objects.equals(playerTeam, breakTeamName)) {
                            player.sendTitle(t(break_title_all_real), t(break_subtitle_all_real));
                        } else if (Objects.equals(playerTeam, breakTeamName)) {
                            player.sendTitle(t(break_title_breakTeam_real), t(break_subtitle_breakTeam_real));
                        }
                    });
                }
            }
            //End
        }.runTaskLater(plugin, 1L);
    }
}
