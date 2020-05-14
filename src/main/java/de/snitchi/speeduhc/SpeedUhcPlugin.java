package de.snitchi.speeduhc;

import de.snitchi.commands.DamageCmd;
import de.snitchi.commands.GameStateCmd;
import de.snitchi.commands.HeadCmd;
import de.snitchi.commands.SetLobbyCmd;
import de.snitchi.commands.StartCmd;
import de.snitchi.commands.StatsCmd;
import de.snitchi.countdown.DeathmatchCount;
import de.snitchi.countdown.EndCount;
import de.snitchi.countdown.IngameCount;
import de.snitchi.countdown.LobbyCount;
import de.snitchi.countdown.ProtectionCount;
import de.snitchi.listener.BlockBreakListener;
import de.snitchi.listener.CraftListener;
import de.snitchi.listener.ImportantListener;
import de.snitchi.listener.InteractListener;
import de.snitchi.listener.JoinListener;
import de.snitchi.listener.PlayerDeathListener;
import de.snitchi.listener.QuitListener;
import de.snitchi.listener.RegenerationListener;
import de.snitchi.manager.GameState;
import de.snitchi.manager.PlayerManager;
import de.snitchi.manager.PlayerState;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpeedUhcPlugin extends JavaPlugin {

  public static GameState gameState = GameState.LOBBY;
  public static PlayerState playerState;

  public static HashMap<UUID, PlayerManager> playermanager = new HashMap<UUID, PlayerManager>();

  private static SpeedUhcPlugin instance;
  private Configuration msgConfig;
  private FileConfiguration userConfig;
  //private FileConfiguration NAMEConfig;

  public static SpeedUhcPlugin getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    instance = this;

    loadConfig();
    msgConfig = loadYml("messages.yml");
    userConfig = loadYml("userdata.yml");

    registerCommands();
    registerListener();
    worldSettings();

    CraftingRecipes.InitialRecipes();
    LobbyCount.start();
    IngameCount.start();
    DeathmatchCount.start();
    EndCount.start();
    ProtectionCount.start();
  }

  public void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  private FileConfiguration loadYml(String name) {
    File file = new File(getDataFolder(), name);
    if (!getDataFolder().exists()) {
      getDataFolder().mkdirs();
    }
    if (!file.exists()) {
      try (InputStream in = getResource(name)) {
        Files.copy(in, file.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return YamlConfiguration.loadConfiguration(file);
  }

  private void registerCommands() {
    // getCommand("Name").setExecutor(new NameCmd());
    getCommand("damage").setExecutor(new DamageCmd());
    getCommand("head").setExecutor(new HeadCmd());
    getCommand("setlobby").setExecutor(new SetLobbyCmd());
    getCommand("stats").setExecutor(new StatsCmd());
    getCommand("gamestate").setExecutor(new GameStateCmd());
    getCommand("start").setExecutor(new StartCmd());
  }

  private void registerListener() {
    // getServer().getPluginManager().registerEvents(new ListenerName(), this);
    getServer().getPluginManager().registerEvents(new RegenerationListener(), this);
    getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    getServer().getPluginManager().registerEvents(new JoinListener(), this);
    getServer().getPluginManager().registerEvents(new QuitListener(), this);
    getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    getServer().getPluginManager().registerEvents(new InteractListener(), this);
    getServer().getPluginManager().registerEvents(new ImportantListener(), this);
    getServer().getPluginManager().registerEvents(new CraftListener(), this);
  }

  public static void worldSettings() {
    Bukkit.getWorld("world").setAutoSave(false);
    Bukkit.getWorld("world").setStorm(false);
    Bukkit.getWorld("world").setTime(800);
    Bukkit.getWorld("world").setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
    Bukkit.getWorld("world").setGameRule(GameRule.DO_WEATHER_CYCLE, false);
    Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

    Bukkit.getWorld("world").getWorldBorder().setSize(1000);
  }

  public Configuration getMsgConfig() {
    return msgConfig;
  }

  public FileConfiguration getUserConfig() {
    return userConfig;
  }

  public void saveUserConfig() {
    try {
      userConfig.save(new File(getDataFolder(), "userdata.yml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
