package de.snitchi.listener;

import de.snitchi.manager.GameState;
import de.snitchi.someapi.ItemBuilder;
import de.snitchi.speeduhc.Messages;
import de.snitchi.speeduhc.Scoreboard;
import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

public class JoinListener implements Listener {

  @EventHandler
  public void onFirstJoin(PlayerJoinEvent event) {

    Player player = event.getPlayer();
    ConfigCheck(player);

    Configuration config = SpeedUhcPlugin.getInstance().getConfig();

    GameState gameState = SpeedUhcPlugin.gameState;

    ItemBuilder builder = new ItemBuilder(Material.MAGMA_CREAM);
    builder.setAmount(1);
    Inventory inventory = player.getInventory();

    switch (gameState) {
      case LOBBY:

        PlayerStuffCheck(player, GameMode.ADVENTURE);

        builder.setDisplayName("§cLobby Verlassen");
        inventory.setItem(8, builder.build());

        Location location = (Location) config.get("Game.Lobby.pos");

        if (location == null) {
          location = Bukkit.getServer().getWorld("world").getSpawnLocation();
        }

        player.teleport(location);

        event.setJoinMessage(Messages.getMsg("Lobby.join", player.getDisplayName()));

        break;
      case INGAME:
      case END:

        PlayerStuffCheck(player, GameMode.SPECTATOR);

        builder.setDisplayName("§cSpiel Verlassen");
        inventory.setItem(8, builder.build());

        event.setJoinMessage(null);
        break;
    }
  }

  public void ConfigCheck(Player player) {

    Configuration config = SpeedUhcPlugin.getInstance().getUserConfig();

    if (!(config.isSet(player.getUniqueId() + ""))) {

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

  public void PlayerStuffCheck(Player player, GameMode gameMode) {

    player.getInventory().clear();
    player.getActivePotionEffects().clear();

    for (PotionEffect effect : player.getActivePotionEffects()) {
      player.removePotionEffect(effect.getType());
    }

    player.setGameMode(gameMode);

    player.setFoodLevel(20);
    player.setHealth(20);
    player.setExp(0.0F);
    player.setLevel(0);

    Scoreboard.setScoreboard(player);
  }
}
