package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ImportantListener implements Listener {

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }
    event.setCancelled(true);
  }

  @EventHandler
  public void onDrop(PlayerDropItemEvent event){
    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }
    event.setCancelled(true);
  }

  @EventHandler
  public void onEntityDamage(EntityDamageByEntityEvent event){
    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }
    event.setCancelled(true);
  }

  @EventHandler
  public void onBlockDamage(EntityDamageByBlockEvent event){
    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }
    event.setCancelled(true);
  }

  @EventHandler
  public void onDamage(EntityDamageEvent event){
    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }
    event.setCancelled(true);
  }

  @EventHandler
  public void onFoodLoose(FoodLevelChangeEvent event){
    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return;
    }
    event.setCancelled(true);
  }
}
