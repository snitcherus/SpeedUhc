package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.manager.PlayerManager;
import de.snitchi.manager.PlayerState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;


public class QuitListener implements Listener {

  @EventHandler
  public void onQuit(PlayerQuitEvent event){

    Player player = event.getPlayer();

    GameState gameState = SpeedUhcPlugin.gameState;

    UUID uuid = player.getUniqueId();

    SpeedUhcPlugin.playermanager.remove(uuid);
    System.out.println("Remove-Player " + uuid);

    switch(gameState){
      case LOBBY:
      case END:
        event.setQuitMessage(Messages.getMsg("Lobby.quit", player.getDisplayName()));
        break;
      case INGAME:
        if(SpeedUhcPlugin.playermanager.size() >= 1){
          SpeedUhcPlugin.gameState = GameState.END;
          Bukkit.broadcastMessage("Test-Ende");
        }
        event.setQuitMessage(Messages.getMsg("Lobby.quit", player.getDisplayName()));
        break;
    }
  }
}
