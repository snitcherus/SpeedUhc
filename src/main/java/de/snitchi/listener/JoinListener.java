package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

  @EventHandler
  public void onFirstJoin(PlayerJoinEvent event){
    Player player = event.getPlayer();

    Configuration userConfig = SpeedUhcPlugin.getInstance().getUserConfig();
    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    GameState gameState = SpeedUhcPlugin.gameState;

    switch(gameState){
      case LOBBY:

        player.getInventory().clear();
        player.getActivePotionEffects().clear();

        Location location = (Location) config.get("Game.Lobby.pos");
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(location);
        Scoreboard.setScoreboard(player);

        event.setJoinMessage(Messages.getMsg("Lobby.join", player.getDisplayName()));

        break;
      case INGAME:
      case END:
        player.setGameMode(GameMode.SPECTATOR);
        event.setJoinMessage(null);
        break;
    }

    if(!(userConfig.isSet(player.getUniqueId() + ""))){

      userConfig.set(player.getUniqueId() + ".Name", player.getDisplayName());
      userConfig.set(player.getUniqueId() + ".Coins", 0);
      userConfig.set(player.getUniqueId() + ".Kills", 0);
      userConfig.set(player.getUniqueId() + ".Deaths", 0);
      userConfig.set(player.getUniqueId() + ".KD", 0);
      userConfig.set(player.getUniqueId() + ".Played_Games", 0);
      userConfig.set(player.getUniqueId() + ".Wins", 0);
      userConfig.set(player.getUniqueId() + ".Loses", 0);
      SpeedUhcPlugin.getInstance().saveUserConfig();

      Scoreboard.setScoreboard(player);
      return;
    }
  }
}
