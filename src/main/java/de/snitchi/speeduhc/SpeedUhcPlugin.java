package de.snitchi.speeduhc;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class SpeedUhcPlugin extends JavaPlugin {

  private static SpeedUhcPlugin instance;
  private Configuration msgConfig;
  private FileConfiguration userConfig;
  //private FileConfiguration NAMEConfig;

  @Override
  public void onEnable() {
    instance = this;

    loadConfig();
    msgConfig = loadYml("messages.yml");
    userConfig = loadYml("userdata.yml");

    registerCommands();
    registerListener();
  }

  public static SpeedUhcPlugin getInstance() {
    return instance;
  }

  public void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  private FileConfiguration loadYml(String name) {
    File file = new File(getDataFolder(), name);
    if (!getDataFolder().exists())
      getDataFolder().mkdirs();
    if (!file.exists()) {
      try (InputStream in = getResource(name)) {
        Files.copy(in, file.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return YamlConfiguration.loadConfiguration(file);
  }

  private void registerListener() {
    // getServer().getPluginManager().registerEvents(new ListenerName(), this);

  }

  private void registerCommands() {
    // getCommand("Name").setExecutor(new NameCmd());

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
