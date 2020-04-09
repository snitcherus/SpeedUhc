package de.snitchi.speeduhc;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class Messages {

  public static String getMsg(String key, String... replaces) {
    Configuration msgConfig = SpeedUhcPlugin.getInstance().getMsgConfig();
    String msg = msgConfig.getString(key);
    if (msg == null) {
      msg = "N/A";
    }
    String message = ChatColor.translateAlternateColorCodes('&', msg);
    for (int i = 0; i < replaces.length; i++) {
      message = message.replace("{" + i + "}", replaces[i]);
    }
    message = message.replace("{prefix}", ChatColor.translateAlternateColorCodes('&', msgConfig.getString("prefix")));
    //message = message.replace("{msg}", ChatColor.translateAlternateColorCodes('&', msgConfig.getString("msgprefix")));
    return message;
  }

  public static void send(Player player, String key, String... replaces) {
    player.sendMessage(getMsg(key, replaces));
  }

  public static void send(CommandSender sender, String key, String... replaces) {
    sender.sendMessage(getMsg(key, replaces));
  }
}
