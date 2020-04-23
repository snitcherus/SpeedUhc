package de.snitchi.speeduhc;

import de.snitchi.countdown.IngameCount;
import de.snitchi.someapi.ScoreboardSet;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class Scoreboard {

  public static void setScoreboard(Player player) {
    Configuration config = SpeedUhcPlugin.getInstance().getUserConfig();

    int kills = config.getInt(player.getUniqueId() + ".Kills");
    int deaths = config.getInt(player.getUniqueId() + ".Deaths");

    double kd = kills;
    if (deaths != 0) {
      kd = (double) kills / (double) deaths;
    }
    NumberFormat formater = NumberFormat.getInstance();
    formater.setMaximumFractionDigits(2);

    String title = Messages.getMsg("Scoreboard.title");
    String empty = Messages.getMsg("Scoreboard.empty");
    String scoreboardKills = Messages.getMsg("Scoreboard.kills", kills + "");
    String scoreboardDeaths = Messages.getMsg("Scoreboard.deaths", deaths + "");
    String scoreboardKD = Messages.getMsg("Scoreboard.kd", formater.format(kd) + "");

    ScoreboardSet setBoard = new ScoreboardSet();
    setBoard.set(player, title, empty, scoreboardKills, scoreboardDeaths,empty, scoreboardKD,
        empty);

  }

  public static void setIngameBoard(Player player){

    String title = Messages.getMsg("Scoreboard.title");
    String empty = Messages.getMsg("Scoreboard.empty");
    String border = Messages.getMsg("Scoreboard.border",
        Bukkit.getServer().getWorld("world").getWorldBorder().getSize() + "");
    String timeToCount = Messages.getMsg("Scoreboard.time", IngameCount.timeToCount + "");

    ScoreboardSet setIngameBoard = new ScoreboardSet();
    setIngameBoard.set(player, title, empty, border, timeToCount);

  }
}
