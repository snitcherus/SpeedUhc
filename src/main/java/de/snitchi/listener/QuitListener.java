package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class QuitListener implements Listener {

  @EventHandler
  public void onQuit(PlayerQuitEvent event){

    Player player = event.getPlayer();

    GameState gameState = SpeedUhcPlugin.gameState;

    switch(gameState){
      case LOBBY:
        event.setQuitMessage(Messages.getMsg("Lobby.quit", player.getDisplayName()));
        break;
      case INGAME:
      case END:
        event.setQuitMessage(null);
        break;
    }
  }
}