package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.manager.PlayerManager;
import de.snitchi.manager.PlayerState;
import de.snitchi.someapi.ItemBuilder;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class JoinListener implements Listener {

  @EventHandler
  public void onFirstJoin(PlayerJoinEvent event){

    Player player = event.getPlayer();
    ConfigCheck(player);

    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    GameState gameState = SpeedUhcPlugin.gameState;

    player.getInventory().clear();
    player.getActivePotionEffects().clear();

    ItemBuilder builder = new ItemBuilder(Material.MAGMA_CREAM);
    builder.setAmount(1);
    Inventory inventory = player.getInventory();

    switch(gameState){
      case LOBBY:

        builder.setDisplayName("§cLobby Verlassen");
        inventory.setItem(8, builder.build());

        Location location = (Location) config.get("Game.Lobby.pos");
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(location);
        Scoreboard.setScoreboard(player);

        event.setJoinMessage(Messages.getMsg("Lobby.join", player.getDisplayName()));

        break;
      case INGAME:
      case END:

        builder.setDisplayName("§cSpiel Verlassen");
        inventory.setItem(8, builder.build());

        player.setGameMode(GameMode.SPECTATOR);
        event.setJoinMessage(null);
        break;
    }
  }

  public void ConfigCheck(Player player){

    Configuration config = SpeedUhcPlugin.getInstance().getUserConfig();

    if(!(config.isSet(player.getUniqueId() + ""))){

      config.set(player.getUniqueId() + ".Name", player.getDisplayName());
      config.set(player.getUniqueId() + ".Coins", 0);
      config.set(player.getUniqueId() + ".Kills", 0);
      config.set(player.getUniqueId() + ".Deaths", 0);
      config.set(player.getUniqueId() + ".KD", 0);
      config.set(player.getUniqueId() + ".Played_Games", 0);
      config.set(player.getUniqueId() + ".Wins", 0);
      config.set(player.getUniqueId() + ".Loses", 0);
      SpeedUhcPlugin.getInstance().saveUserConfig();

      Scoreboard.setScoreboard(player);
      return;
    }
  }
}
