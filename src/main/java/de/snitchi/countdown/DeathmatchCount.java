package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;

public class DeathmatchCount {

  private static int startID;
  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("deathmatchTimeToCount");

  public static void start(){

    //runnable
    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {


      if(SpeedUhcPlugin.gameState != GameState.DEATHMATCH){
        return;
      }

      Bukkit.broadcastMessage(Messages.getMsg("Deathmatch.count", timeToCount + ""));

      if(timeToCount == 0){

        SpeedUhcPlugin.gameState = GameState.END;

      }
      timeToCount--;
    },0L,1200L);

  }

}
