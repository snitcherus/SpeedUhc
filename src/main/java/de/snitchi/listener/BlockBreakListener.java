package de.snitchi.listener;

import com.google.common.collect.Maps;
import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

  private final Map<Material, Drop> dropMapper = Maps.newHashMap();

  public BlockBreakListener() {
    dropMapper.put(Material.COAL_ORE, new Drop(1, Material.TORCH, 2));
    dropMapper.put(Material.IRON_ORE, new Drop(1, Material.IRON_INGOT, 2));
    dropMapper.put(Material.OAK_LOG, new Drop(Material.OAK_PLANKS, 4));
  }

  @EventHandler
  public void onBreak(BlockBreakEvent event) {
    Block block = event.getBlock();
    Player player = event.getPlayer();

    if (SpeedUhcPlugin.gameState != GameState.INGAME) {
      return;
    }

    Drop drop = dropMapper.get(block.getType());
    if (drop == null) {
      return;
    }

    player.giveExp(drop.getExperience());
    block.setType(Material.AIR);
    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), drop.getItemStack());
  }

  private final class Drop {

    private final int experience;
    private final ItemStack itemStack;

    private Drop(ItemStack itemStack) {
      this(0, itemStack);
    }

    private Drop(int experience, ItemStack itemStack) {
      this.experience = experience;
      this.itemStack = itemStack;
    }

    private Drop(int experience, Material material, int amount) {
      this(experience, new ItemStack(material, amount));
    }

    private Drop(int experience, Material material) {
      this(experience, new ItemStack(material));
    }

    private Drop(Material material, int amount) {
      this(0, new ItemStack(material, amount));
    }

    private Drop(Material material) {
      this(0, new ItemStack(material));
    }

    public ItemStack getItemStack() {
      return itemStack;
    }

    public int getExperience() {
      return experience;
    }
  }
}
