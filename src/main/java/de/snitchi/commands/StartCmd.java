package de.snitchi.commands;

import de.snitchi.countdown.LobbyCount;
import de.snitchi.manager.GameState;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class StartCmd implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    if (!(sender instanceof Player)) {
      return true;
    }

    if (SpeedUhcPlugin.gameState != GameState.LOBBY) {
      return true;
    }

    Player player = (Player) sender;

    if (!player.hasPermission("uhc.start") || !player.isOp()) {
      Messages.send(player, "System.noPerms");
      return true;
    }

    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    int playersToStart = config.getInt("playersToStart");

    if (Bukkit.getOnlinePlayers().size() < playersToStart) {

      Messages.send(player, "Lobby.notStart");

      return true;
    }

    LobbyCount.timeToCount = 5;
    Messages.send(player, "Lobby.start");

    return false;
  }
}
