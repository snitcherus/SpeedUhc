package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;

public class IngameCount {

  private static int startID;
  private static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("ingameTimeToCount");

  public static void start() {

    Bukkit.getWorld("world").getWorldBorder().setSize(300);

    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if (SpeedUhcPlugin.gameState != GameState.INGAME) {
        return;
      }

      switch (timeToCount) {
        case 60:
          setBorder(300, 0, true);
          break;
        case 50:
          setBorder(280, 20, false);
          break;
        case 40:
          setBorder(260, 20, true);
          break;
        case 30:
          setBorder(240, 20, false);
          break;
        case 20:
          setBorder(220, 20, true);
          break;
        case 10:
          setBorder(200, 20, true);
          break;
        case 9:
          setBorder(180, 20, false);
          break;
        case 8:
          setBorder(160, 20, false);
          break;
        case 7:
          setBorder(140, 20, false);
          break;
        case 6:
          setBorder(120, 20, false);
          break;
        case 5:
          setBorder(100, 20, true);
          break;
        case 4:
          setBorder(80, 20, true);
          break;
        case 3:
          setBorder(60, 20, true);
          break;
        case 2:
          setBorder(40, 20, true);
          break;
        case 1:
          setBorder(20, 20, true);
          break;
        case 0:
          Bukkit.broadcastMessage(Messages.getMsg("Ingame.deathmatch"));
          SpeedUhcPlugin.gameState = GameState.DEATHMATCH;

          Bukkit.getScheduler().cancelTask(startID);
          break;
      }
      timeToCount--;
    }, 0, 1200); //1200 Ticks = One Minute
  }

  public static void setBorder(int size, int time, boolean ingameCount) {
    Bukkit.broadcastMessage(Messages.getMsg("Ingame.border"));
    Bukkit.getWorld("world").getWorldBorder().setSize(size, time);

    if (ingameCount == true) {
      Bukkit.broadcastMessage(Messages.getMsg("Ingame.count", timeToCount + ""));
    }
  }
}
