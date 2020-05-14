package de.snitchi.commands;

import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameStateCmd implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    Player player = (Player) sender;

    player.sendMessage(SpeedUhcPlugin.gameState + "");

    return true;
  }
}
