package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ImportantListener implements Listener {

  @EventHandler
  public void onClick(InventoryClickEvent event) {

    Player player = (Player) event.getWhoClicked();

    if(SpeedUhcPlugin.gameState == GameState.LOBBY){
      System.out.println("EntityDamage");
      event.setCancelled(true);
      return;
    }

    if(SpeedUhcPlugin.gameState == GameState.END){
      event.setCancelled(true);
      return;
    }

    if(player.getGameMode() == GameMode.SPECTATOR){
      event.setCancelled(true);
      return;
    }

    event.setCancelled(false);
  }

  @EventHandler
  public void onDrop(PlayerDropItemEvent event) {

    Player player = event.getPlayer();
    if(SpeedUhcPlugin.gameState == GameState.LOBBY){
      System.out.println("EntityDamage");
      event.setCancelled(true);
      return;
    }

    if(SpeedUhcPlugin.gameState == GameState.END){
      event.setCancelled(true);
      return;
    }

    if(player.getGameMode() == GameMode.SPECTATOR){
      event.setCancelled(true);
      return;
    }

    event.setCancelled(false);
  }

  @EventHandler
  public void onDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) event.getEntity();

    if(SpeedUhcPlugin.gameState == GameState.LOBBY){
      System.out.println("EntityDamage");
      event.setCancelled(true);
      return;
    }

    if(SpeedUhcPlugin.gameState == GameState.END){
      event.setCancelled(true);
      return;
    }

    if(player.getGameMode() == GameMode.SPECTATOR){
      event.setCancelled(true);
      return;
    }

    System.out.println("EntityDamage");

    event.setCancelled(false);
  }

  @EventHandler
  public void onFoodLoose(FoodLevelChangeEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) event.getEntity();
    if (SpeedUhcPlugin.gameState != GameState.LOBBY || SpeedUhcPlugin.gameState != GameState.END ||
        player.getGameMode() != GameMode.SPECTATOR) {
      return;
    }

    System.out.println("FoodChange");
    event.setCancelled(true);
  }
}
