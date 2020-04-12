package de.snitchi.speeduhc;

import de.snitchi.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class LobbyCount {

  private static int startID;
  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("timeToCount");

  public static void start() {

    Configuration config = SpeedUhcPlugin.getInstance().getConfig();
    int playersToStart = config.getInt("playersToStart");

    SpeedUhcPlugin.gameState = GameState.LOBBY;

    //runnable
    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if (Bukkit.getOnlinePlayers().size() < playersToStart) {

          if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
            System.out.println("wrong gamestate");
            return;
          }

          for(Player player : Bukkit.getOnlinePlayers()){
            player.setLevel(0);
            player.setExp(0);
          }
        return;
      }

      if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
        Bukkit.getScheduler().cancelTask(startID);
        return;
      }

      for (Player player : Bukkit.getOnlinePlayers()) {
        player.setLevel(timeToCount);
        player.setExp(timeToCount / 180F);
      }

      if (timeToCount == 60 || timeToCount == 30 || timeToCount < 11 && timeToCount > 0) {
        Bukkit.broadcastMessage(Messages.getMsg("Lobby.count", timeToCount + ""));
        for (Player player : Bukkit.getOnlinePlayers()) {
          player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
        }
      }

      if (timeToCount == 0) {
        Bukkit.broadcastMessage(Messages.getMsg("Game.begin"));

        for (Player player : Bukkit.getOnlinePlayers()) {
          player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 7.0F, 7.0F);

          //teleport einfügen
          Location location = (Location) config.get("Game.Lobby.pos");
          player.teleport(location);

          player.getInventory().clear();
          player.setFoodLevel(20);
          player.setHealth(20);
          player.setExp(0.0F);
          player.setLevel(0);
        }

        SpeedUhcPlugin.gameState = GameState.INGAME;
        Bukkit.getScheduler().cancelTask(startID);
      }
      timeToCount--;
    }, 0L, 20L);
  }
}