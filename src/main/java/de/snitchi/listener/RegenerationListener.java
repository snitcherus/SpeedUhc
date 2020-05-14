package de.snitchi.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RegenerationListener implements Listener {

  @EventHandler
  public void onRegeneration(EntityRegainHealthEvent event) {
    if (event.getRegainReason() != EntityRegainHealthEvent.RegainReason.MAGIC_REGEN) {
      event.setCancelled(true);
      return;
    }
    event.setCancelled(false);
  }

  @EventHandler
  public void onConsume(PlayerItemConsumeEvent event) {
    Player player = event.getPlayer();
    ItemStack item = event.getItem();
    if (item.getType() != Material.GOLDEN_APPLE) {
      return;
    }

    switch (item.getItemMeta().getDisplayName()) {
      case "§3Player Head":
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
        break;
      case "§3Golden Apple":
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
        break;
    }
  }
}
