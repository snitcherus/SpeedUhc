package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

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
          setBorder(300);
          break;
        case 50:
          setBorder(280);
          break;
        case 40:
          setBorder(260);
          break;
        case 30:
          setBorder(240);
          break;
        case 20:
          setBorder(220);
          break;
        case 10:
          setBorder(200);
          break;
        case 9:
          setBorder(180);
          break;
        case 8:
          setBorder(160);
          break;
        case 7:
          setBorder(140);
          break;
        case 6:
          setBorder(120);
          break;
        case 5:
          setBorder(100);
          break;
        case 4:
          setBorder(80);
          break;
        case 3:
          setBorder(60);
          break;
        case 2:
          setBorder(40);
          break;
        case 1:
          setBorder(20);
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

  public static void setBorder(int size){
    Bukkit.getWorld("world").getWorldBorder().setSize(size);
  }
}
