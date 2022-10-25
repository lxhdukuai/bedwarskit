package github.tsffish.bedwarskit.util;

public class RelBreakTitle {
    public static String bt(String text, String breakTeamColor, String breakTeamName, String breakPlayerTeamColor, String breakPlayerName, String breakPlayerTeamName){

        return text.replace("{BreakTeamColor}", breakTeamColor).replace("{BreakTeamName}", breakTeamName).replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).replace("{BreakPlayerName}", breakPlayerName).replace("{breakPlayerTeamName}", breakPlayerTeamName);
    }
}
