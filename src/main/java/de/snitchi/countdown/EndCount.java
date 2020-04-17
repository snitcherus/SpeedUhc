package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class EndCount {

  private static int startID;
  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("endTimeToCount");

  public static void start(){
    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    //runnable
    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {


      if(SpeedUhcPlugin.gameState != GameState.END){
        return;
      }

      if(!(SpeedUhcPlugin.playermanager.size() <= 1)){
        return;
      }

      if(timeToCount == 0){

        for(Player player : Bukkit.getOnlinePlayers()){
          player.kickPlayer("");
        }

      }

    },0L,20L);

  }

}
