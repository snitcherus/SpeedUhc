package de.snitchi.commands;

import de.snitchi.someapi.ItemBuilder;
import de.snitchi.speeduhc.Messages;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HeadCmd implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    if (!(player.isOp())) {
      return true;
    }

    if (args.length != 1) {
      Messages.send(player, "Head.usage");
      return true;
    }

    int amount = Integer.parseInt(args[0]);

    if (amount <= 0) {
      Messages.send(player, "Head.usage");
      return true;
    }

    ItemBuilder builder = new ItemBuilder(Material.GOLDEN_APPLE);
    builder.setDisplayName("§3Player Head");
    builder.setAmount(amount);

    Inventory inventory = player.getInventory();
    inventory.addItem(builder.build());

    Messages.send(player, "Head.success");

    return true;
  }
}
