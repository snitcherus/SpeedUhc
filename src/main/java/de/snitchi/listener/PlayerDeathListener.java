package de.snitchi.listener;

import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerDeathListener implements Listener {

  @EventHandler
  public void onDeath(EntityDeathEvent event) {

    if (!(event.getEntity() instanceof Player)) {
      return;
    }

    Player player = (Player) event.getEntity();

    Configuration userConfig = SpeedUhcPlugin.getInstance().getUserConfig();

    //attacker
    Player target = player.getKiller();

    if(target == null){
      int suicide = userConfig.getInt(player.getUniqueId() + ".Deaths") + 1;
      userConfig.set(player.getUniqueId() + ".Deaths", suicide);
      SpeedUhcPlugin.getInstance().saveUserConfig();
      Bukkit.broadcastMessage(Messages.getMsg("System.suicide", player.getDisplayName()));

      Scoreboard.setScoreboard(player);
      return;
    }

    int kill = userConfig.getInt(target.getUniqueId() + ".Kills") + 1;
    int death = userConfig.getInt(player.getUniqueId() + ".Deaths") + 1;

    userConfig.set(target.getUniqueId() + ".Kills", kill);
    userConfig.set(player.getUniqueId() + ".Deaths", death);
    SpeedUhcPlugin.getInstance().saveUserConfig();

    Scoreboard.setScoreboard(player);

    player.setGameMode(GameMode.SPECTATOR);

    Messages.send(player, ".System.death", target.getDisplayName());
    Messages.send(target, ".System.killer", player.getDisplayName());

  }
}
