package de.snitchi.commands;

import de.snitchi.speeduhc.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DamageCmd implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

    if(!(sender instanceof Player)){
      return true;
    }

    Player player = (Player) sender;

    if(!(player.isOp())){
      return true;
    }

    if(args.length > 2){
      Messages.send(player, "Damage.usage");
      return true;
    }

    switch(args.length){
      case 0:
        Messages.send(player, "Damage.usage");
        break;
      case 1:

        int damage = Integer.parseInt(args[0]);

        if(damage <= 0){
          Messages.send(player, "Damage.usage");
          break;
        }

        player.damage(damage);
        Messages.send(player, "Damage.success", damage + "");
        break;
      case 2:

        damage = Integer.parseInt(args[1]);

        if(damage <= 0){
          Messages.send(player, "Damage.usage");
          break;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null){
          Messages.send(player, "System.playerNotFound", args[0]);
          break;
        }

        if(target == player) {
          player.damage(damage);
          Messages.send(player, "Damage.success", damage + "");
          break;
        }

        target.damage(damage);
        Messages.send(player, "Damage.successTarget", target.getDisplayName(), damage + "");
        Messages.send(target, "Damage.target", damage + "", player.getDisplayName());
        break;
    }

    return false;
  }
}
