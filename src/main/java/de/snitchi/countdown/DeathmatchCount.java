package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;

public class DeathmatchCount {

  public static int timeToCount = SpeedUhcPlugin.getInstance()
                                                .getConfig()
                                                .getInt("deathmatchTimeToCount");
  private static int startID;

  public static void start() {

    //runnable
    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if (SpeedUhcPlugin.gameState != GameState.DEATHMATCH) {
        return;
      }

      if (SpeedUhcPlugin.playermanager.size() <= 1) {
        SpeedUhcPlugin.gameState = GameState.END;
        Bukkit.getScheduler().cancelTask(startID);
        return;
      }

      switch (timeToCount) {
        case 600:
        case 300:
        case 240:
        case 180:
        case 120:
        case 60:
          Bukkit.broadcastMessage(Messages.getMsg("Deathmatch.count", timeToCount / 60 + ""));
          break;
        case 0:
          Bukkit.broadcastMessage(Messages.getMsg("Deathmatch.end"));
          SpeedUhcPlugin.gameState = GameState.END;
      }

      timeToCount--;
    }, 0L, 20L);
  }
}
