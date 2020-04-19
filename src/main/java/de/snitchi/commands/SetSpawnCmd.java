package de.snitchi.commands;

import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SetSpawnCmd implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    if (!(sender instanceof Player)) {
      return true;
    }
    Player player = (Player) sender;
    setSpawn(player, args[0]);

    Messages.send(player, "Spawn.set", args[0]);

    return false;
  }


  public void setSpawn(Player player, String args){
    Configuration config = SpeedUhcPlugin.getInstance().getConfig();
    config.set("Spawn." + args, player.getLocation());
    SpeedUhcPlugin.getInstance().saveConfig();
  }
}
