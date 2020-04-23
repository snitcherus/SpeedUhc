package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;

public class ProtectionCount {

  private static int startID;
  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("protectionTime");

  public static void start(){

    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if(SpeedUhcPlugin.gameState != GameState.INGAME){
        return;
      }

      switch(timeToCount){
        case 10:
        case 5:
        case 4:
        case 3:
        case 2:
        case 1:
          Bukkit.broadcastMessage(Messages.getMsg("Ingame.protectionTime", timeToCount + ""));
          break;
        case 0:
          Bukkit.getScheduler().cancelTask(startID);
          Bukkit.broadcastMessage(Messages.getMsg("Ingame.protectionTimeEnd", timeToCount + ""));
          break;
      }
      timeToCount--;
    },0L, 1200L);

  }

}
