package de.snitchi.listener;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class RegenerationListener implements Listener {

  @EventHandler
  public void onRegeneration(EntityRegainHealthEvent event){
    Player player = (Player) event.getEntity();
    if(event.getRegainReason() != EntityRegainHealthEvent.RegainReason.EATING){
      return;
    }
    event.setCancelled(true);
  }
}
