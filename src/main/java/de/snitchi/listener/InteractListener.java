package de.snitchi.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

  @EventHandler
  public void onInteract(PlayerInteractEvent event){

    Player player = event.getPlayer();

    switch(event.getItem().getItemMeta().getDisplayName()){
      case "§cLobby Verlassen":
      case "§cSpiel Verlassen":
        player.kickPlayer("");
        break;
    }

  }
}
