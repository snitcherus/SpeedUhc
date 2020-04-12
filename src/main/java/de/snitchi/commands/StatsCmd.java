package de.snitchi.commands;


import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class StatsCmd implements CommandExecutor {

  private static Configuration config = SpeedUhcPlugin.getInstance().getUserConfig();

  private static NumberFormat formater = NumberFormat.getInstance();

  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    int kills = config.getInt(player.getUniqueId() + ".Kills");
    int deaths = config.getInt(player.getUniqueId() + ".Deaths");
    formater.setMaximumFractionDigits(2);

    double kd = kills;

    if (kd != 0) {
      kd = (double) kills / (double) deaths;
    }

    if (args.length == 0) {
      Messages.send(player, "Stats.stats", kills + "", deaths + "", formater.format(kd) + "");
    } else {
      switch (args[0].toLowerCase()) {
        case "reset":

          if (!player.hasPermission("uhc.reset") || !player.isOp()) {
            Messages.send(player, "System.noPerms");
            break;
          }

          if (args.length != 2) {
            Messages.send(player, "Stats.resetUsage");
            break;
          }

          Player target = Bukkit.getPlayer(args[1]);

          if (target == null) {
            Messages.send(player, "System.playerNotFound", args[1]);
            break;
          }

          if (target == player) {
            config.set(player.getUniqueId() + ".Kills", 0);
            config.set(player.getUniqueId() + ".Deaths", 0);
            SpeedUhcPlugin.getInstance().saveUserConfig();
            Scoreboard.setScoreboard(player);
            Messages.send(player, "Stats.targetReset");
            break;
          }

          config.set(target.getUniqueId() + ".Kills", 0);
          config.set(target.getUniqueId() + ".Deaths", 0);
          SpeedUhcPlugin.getInstance().saveUserConfig();
          Scoreboard.setScoreboard(target);
          Messages.send(target, "Stats.targetReset");
          Messages.send(player, "Stats.reset", target.getDisplayName());
          break;
        default:
          if (args.length != 1) {
            Messages.send(player, "Stats.usage");
            return true;
          }

          target = Bukkit.getPlayer(args[0]);

          if (target == null) {
            @SuppressWarnings("deprecation")
            OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);

            if (!(config.isSet(offlineTarget.getUniqueId() + ".Kills"))) {
              Messages.send(player, "System.playerNotFound", args[0]);
              break;
            }

            kills = config.getInt(offlineTarget.getUniqueId() + ".Kills");
            deaths = config.getInt(offlineTarget.getUniqueId() + ".Deaths");
            kd = kills;
            if (deaths != 0) {
              kd = (double) kills / (double) deaths;
            }

            Messages.send(player, "Stats.targetStats", offlineTarget.getName(), kills + "", deaths + "",
                formater.format(kd) + "");
            break;
          }

          if (target == player) {
            Messages.send(player, "Stats.stats", kills + "", deaths + "", formater.format(kd) + "");
            break;
          }

          initialTargetStats(player, target);
          break;
      }
    }

    return true;
  }

  /**
   * @param player command sender
   * @param target stats target
   */

  public static void initialTargetStats(Player player, Player target) {

    int kills = config.getInt(target.getUniqueId() + ".Kills");
    int deaths = config.getInt(target.getUniqueId() + ".Deaths");
    double kd = kills;
    if (deaths != 0) {
      kd = (double) kills / (double) deaths;
    }

    Messages.send(player, "Stats.targetStats", target.getName(), kills + "", deaths + "", formater.format(kd) + "");

  }
}
