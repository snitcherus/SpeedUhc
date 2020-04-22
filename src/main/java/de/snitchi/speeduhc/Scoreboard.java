package de.snitchi.speeduhc;

import de.snitchi.someapi.ScoreboardSet;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class Scoreboard {

  public static void setScoreboard(Player player) {
    Configuration config = SpeedUhcPlugin.getInstance().getUserConfig();

    int coins = config.getInt(player.getUniqueId() + ".Coins");
    int kills = config.getInt(player.getUniqueId() + ".Kills");
    int deaths = config.getInt(player.getUniqueId() + ".Deaths");

    double kd = kills;
    if (deaths != 0) {
      kd = (double) kills / (double) deaths;
    }
    NumberFormat formater = NumberFormat.getInstance();
    formater.setMaximumFractionDigits(2);

    String title = Messages.getMsg("Scoreboard.title");
    String line1 = Messages.getMsg("Scoreboard.empty");
    String scoreboardKills = Messages.getMsg("Scoreboard.kills", kills + "");
    String scoreboardDeaths = Messages.getMsg("Scoreboard.deaths", deaths + "");
    String line6 = Messages.getMsg("Scoreboard.empty");
    String scoreboardKD = Messages.getMsg("Scoreboard.kd", formater.format(kd) + "");
    String line8 = Messages.getMsg("Scoreboard.empty");

    ScoreboardSet setBoard = new ScoreboardSet();
    setBoard.set(player, title, line1, scoreboardKills, scoreboardDeaths,line6, scoreboardKD,
        line8);

  }
}
