package de.snitchi.manager;

import de.snitchi.speeduhc.SpeedUhcPlugin;
import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerManager implements Listener {

  private UUID uuid;
  private PlayerState playerState = SpeedUhcPlugin.playerState;

  public PlayerManager(UUID uuid, PlayerState playerState){
    this.setUuid(uuid);
    this.setPlayerState(playerState);
  }

  public UUID getUuid(){
    return uuid;
  }

  public void setUuid(UUID uuid){
    this.uuid = uuid;
  }

  public void setPlayerState(PlayerState playerState){
    this.playerState = playerState;
  }

}
