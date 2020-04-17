package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {

    Player player = event.getPlayer();

    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }

    if(player.getInventory().getHeldItemSlot() != 8){
      return;
    }

    switch (event.getItem().getItemMeta().getDisplayName()) {
      case "§cLobby Verlassen":
      case "§cSpiel Verlassen":
        player.kickPlayer("");
        break;
    }

  }
}
