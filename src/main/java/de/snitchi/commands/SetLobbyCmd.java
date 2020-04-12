package de.snitchi.commands;

import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SetLobbyCmd implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;
    if (!player.hasPermission("uhc.setlobby") || !player.isOp()) {
      Messages.send(player, "System.noPerms");
      return true;
    }

    if(args.length != 0){
      Messages.send(player, "Lobby.usage");
      return true;
    }

    Configuration config = SpeedUhcPlugin.getInstance().getConfig();
    config.set("Game.Lobby.pos", player.getLocation());
    SpeedUhcPlugin.getInstance().saveConfig();

    Messages.send(player, "Lobby.success");

    return false;
  }
}
