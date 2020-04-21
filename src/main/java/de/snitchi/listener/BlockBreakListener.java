package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

  private ItemStack itemStack = new ItemStack(Material.AIR);

  @EventHandler
  public void onBreak(BlockBreakEvent event) {

    Block block = event.getBlock();
    Player player = event.getPlayer();

    if (SpeedUhcPlugin.gameState != GameState.INGAME) {
      return;
    }

    switch (block.getType()) {
      case IRON_ORE:
        instantOreSmelt(player, Material.IRON_INGOT, block);
        break;
      case GOLD_ORE:
        instantOreSmelt(player, Material.GOLD_INGOT, block);
        break;
      case COAL_ORE:
        instantOreSmelt(player, Material.TORCH, block);
        break;
      case ACACIA_LOG:
        instantLogSmelt(Material.ACACIA_PLANKS, block);
        break;
      case BIRCH_LOG:
        instantLogSmelt(Material.BIRCH_PLANKS, block);
        break;
      case DARK_OAK_LOG:
        instantLogSmelt(Material.DARK_OAK_PLANKS, block);
        break;
      case JUNGLE_LOG:
        instantLogSmelt(Material.JUNGLE_PLANKS, block);
        break;
      case OAK_LOG:
        instantLogSmelt(Material.OAK_PLANKS, block);
        break;
      case SPRUCE_LOG:
        instantLogSmelt(Material.SPRUCE_PLANKS, block);
        break;
      case GRASS_BLOCK:
        instantBlockSmelt(Material.DIRT, block);
        break;
      default:
        break;
    }
  }

  /**
   * @param player   the block breaker
   * @param material the dropped item
   * @param block    the breaked block
   */

  public void instantOreSmelt(Player player, Material material, Block block) {

    if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_PICKAXE)) {
      return;
    }
    if (player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_PICKAXE)) {
      return;
    }

    block.setType(Material.AIR);
    player.giveExp(2);
    itemStack = new ItemStack(material);
    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), itemStack);
  }

  /**
   * @param material the dropped item
   * @param block    the breaked block
   */
  public void instantLogSmelt(Material material, Block block) {
    block.setType(Material.AIR);
    itemStack = new ItemStack(material);
    itemStack.setAmount(4);
    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), itemStack);
  }

  /**
   * @param material the dropped item
   * @param block    the breaked block
   */
  public void instantBlockSmelt(Material material, Block block) {
    block.setType(Material.AIR);
    itemStack = new ItemStack(material);
    itemStack.setAmount(1);
    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), itemStack);
  }
}
