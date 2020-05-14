package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class EndCount {

  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("endTimeToCount");
  private static int startID;

  public static void start() {
    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    //runnable
    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if (SpeedUhcPlugin.gameState != GameState.END) {
        return;
      }

      Bukkit.broadcastMessage(Messages.getMsg("End.count", timeToCount + ""));

      if (timeToCount == 0) {

        for (Player player : Bukkit.getOnlinePlayers()) {
          player.kickPlayer("");
        }

        Bukkit.shutdown();
      }
      timeToCount--;
    }, 0L, 20L);
  }
}
