package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.someapi.ItemBuilder;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class PlayerDeathListener implements Listener {

  private ItemBuilder builder = new ItemBuilder(Material.GOLDEN_APPLE);

  @EventHandler
  public void onDeath(EntityDeathEvent event) {

    if (!(event.getEntity() instanceof Player)) {
      return;
    }

    if (SpeedUhcPlugin.gameState != GameState.INGAME) {
      return;
    }

    Player player = (Player) event.getEntity();
    UUID uuid = player.getUniqueId();

    Configuration userConfig = SpeedUhcPlugin.getInstance().getUserConfig();

    //attacker
    Player target = player.getKiller();

    if (target == null) {

      //Stats

      int suicide = userConfig.getInt(player.getUniqueId() + ".Deaths") + 1;
      userConfig.set(player.getUniqueId() + ".Deaths", suicide);
      SpeedUhcPlugin.getInstance().saveUserConfig();

      BuildPlayerHead((PlayerDeathEvent) event);

      String suicideMessage = Messages.getMsg("System.suicide", player.getDisplayName());
      ((PlayerDeathEvent) event).setDeathMessage(suicideMessage);

      SpeedUhcPlugin.playermanager.remove(uuid);

      if (SpeedUhcPlugin.playermanager.size() >= 1) {
        SpeedUhcPlugin.gameState = GameState.END;
        return;
      }
      return;
    }

    //Stats
    int kill = userConfig.getInt(target.getUniqueId() + ".Kills") + 1;
    int death = userConfig.getInt(player.getUniqueId() + ".Deaths") + 1;

    userConfig.set(target.getUniqueId() + ".Kills", kill);
    userConfig.set(player.getUniqueId() + ".Deaths", death);
    SpeedUhcPlugin.getInstance().saveUserConfig();

    //PlayerHead
    BuildPlayerHead((PlayerDeathEvent) event);

    player.setGameMode(GameMode.SPECTATOR);

    //PlayerManager stuff
    SpeedUhcPlugin.playermanager.remove(uuid);

    if (SpeedUhcPlugin.playermanager.size() <= 1) {
      SpeedUhcPlugin.gameState = GameState.END;
      return;
    }

    String killMessage = Messages.getMsg("System.kill", player.getDisplayName(), target.getDisplayName());
    ((PlayerDeathEvent) event).setDeathMessage(killMessage);

    //Messages
    Messages.send(player, ".System.death", target.getDisplayName());
    Messages.send(target, ".System.killer", player.getDisplayName());

  }

  public void BuildPlayerHead(PlayerDeathEvent event) {
    builder.setDisplayName("§3Player Head");
    event.getDrops().add(builder.build());
  }
}
