package de.snitchi.countdown;

import de.snitchi.manager.GameState;
import de.snitchi.manager.PlayerManager;
import de.snitchi.manager.PlayerState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class LobbyCount {

  public static int timeToCount = SpeedUhcPlugin.getInstance().getConfig().getInt("timeToCount");
  private static int startID;

  public static void start() {

    Configuration config = SpeedUhcPlugin.getInstance().getConfig();
    int playersToStart = config.getInt("playersToStart");

    int spawnRadius = config.getInt("spawnRadius");
    int spawnY = config.getInt("spawnY");

    //runnable
    startID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpeedUhcPlugin.getInstance(), () -> {

      if (Bukkit.getOnlinePlayers().size() < playersToStart) {

        if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
          return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
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

          player.setGameMode(GameMode.SURVIVAL);

          UUID uuid = player.getUniqueId();
          SpeedUhcPlugin.playermanager.put(uuid, new PlayerManager(uuid, PlayerState.ALIVE));
          System.out.println("Add-Player " + uuid);

          player.getInventory().clear();
          player.setFoodLevel(20);
          player.setHealth(20);
          player.setExp(0.0F);
          player.setLevel(0);

          Random random = new Random();
          int randomX = random.nextInt(spawnRadius);
          int randomZ = random.nextInt(spawnRadius);

          Scoreboard.setIngameBoard(player);

          Location location = new Location(Bukkit.getServer().getWorld("world"), randomX, spawnY,
              randomZ);
          player.teleport(location);
        }

        SpeedUhcPlugin.gameState = GameState.INGAME;
        Bukkit.getScheduler().cancelTask(startID);
      }

      timeToCount--;
    }, 0L, 20L);
  }
}

