package de.snitchi.speeduhc;

import de.snitchi.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;

public class IngameCount {

  private static int startID;
  private static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("ingameTimeToCount");

  public static void start(){
    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if(SpeedUhcPlugin.gameState != GameState.INGAME){
        return;
      }

      switch(timeToCount){
        case 60:
        case 40:
        case 20:
        case 10:
        case 5:
        case 4:
        case 3:
        case 2:
        case 1:
          Bukkit.broadcastMessage(Messages.getMsg("Ingame.count", timeToCount + ""));
            break;
        case 0:
          Bukkit.broadcastMessage(Messages.getMsg("Ingame.deathmatch"));
          SpeedUhcPlugin.gameState = GameState.DEATHMATCH;

          Bukkit.getScheduler().cancelTask(startID);
          break;
      }
      timeToCount--;
    },0,1200); //1200 Ticks = One Minute
  }

}
