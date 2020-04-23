package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IngameCount {

  private static int startID;
  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("ingameTimeToCount");

  public static void start() {

    Bukkit.getWorld("world").getWorldBorder().setSize(300);

    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if (SpeedUhcPlugin.gameState != GameState.INGAME) {
        return;
      }

      for(Player player : Bukkit.getOnlinePlayers()){
        Scoreboard.setIngameBoard(player);
      }

      switch (timeToCount) {
        case 3600:
          setBorder(300, 0, true);
          break;
        case 3000:
          setBorder(280, 20, false);
          break;
        case 2400:
          setBorder(260, 20, true);
          break;
        case 1800:
          setBorder(240, 20, false);
          break;
        case 1200:
          setBorder(220, 20, true);
          break;
        case 600:
          setBorder(200, 20, true);
          break;
        case 540:
          setBorder(180, 20, false);
          break;
        case 480:
          setBorder(160, 20, false);
          break;
        case 420:
          setBorder(140, 20, false);
          break;
        case 360:
          setBorder(120, 20, false);
          break;
        case 300:
          setBorder(100, 20, true);
          break;
        case 240:
          setBorder(80, 20, true);
          break;
        case 180:
          setBorder(60, 20, true);
          break;
        case 120:
          setBorder(40, 20, true);
          break;
        case 60:
          setBorder(20, 20, true);
          break;
        case 0:
          Bukkit.broadcastMessage(Messages.getMsg("Ingame.deathmatch"));
          SpeedUhcPlugin.gameState = GameState.DEATHMATCH;

          Bukkit.getScheduler().cancelTask(startID);
          break;
      }
      timeToCount--;
    }, 0, 20); //1200 Ticks = One Minute
  }

  /**
   * @param size new border size
   * @param time time the border need to shrink
   * @param ingameCount ingame message of the ingameCount
   */

  public static void setBorder(int size, int time, boolean ingameCount) {
    //Bukkit.broadcastMessage(Messages.getMsg("Ingame.border"));

    String title = Messages.getMsg("Scoreboard.title");
    String border = Messages.getMsg("Ingame.border");

    for(Player player : Bukkit.getOnlinePlayers()){
      player.sendTitle(title, border, 10, 70, 20);
    }

    Bukkit.getWorld("world").getWorldBorder().setSize(size, time);

    if (ingameCount == true) {
      Bukkit.broadcastMessage(Messages.getMsg("Ingame.count", timeToCount / 60 + ""));
    }
  }
}
