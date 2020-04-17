package de.snitchi.listener;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftListener implements Listener {

  @EventHandler
  public void onCraft(CraftItemEvent event){

    Player player = (Player) event.getWhoClicked();

    if(!event.getRecipe().getResult().containsEnchantment(Enchantment.DAMAGE_ALL)){
      return;
    }

    if(player.getLevel() < 1){
      event.setCancelled(true);
      return;
    }

    player.setLevel(player.getLevel() - 1);
  }
}
