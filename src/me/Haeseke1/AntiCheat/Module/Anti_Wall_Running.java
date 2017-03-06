package me.Haeseke1.AntiCheat.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Anti_Wall_Running {
	
	public static List<Anti_Wall_Running> players = new ArrayList<>();
	
	public static boolean anti_wall_running;
	public static boolean banPlayer;
	
	private final UUID player;
	private Location playerLoc;
	
	public Anti_Wall_Running(UUID player, Location loc) {
		this.player = player;
		this.playerLoc = loc;
		players.add(this);
	}

	public UUID getPlayer() {
		return player;
	}
	
	public boolean isPlayer(UUID uuid) {
		return this.player.equals(uuid);
	}
	
	@SuppressWarnings("deprecation")
	public boolean isPossible(Player player, Location loc){
		if(player.getGameMode().equals(GameMode.SPECTATOR)){
			playerLoc = loc;
			return true;
		}
		if(isLocation(loc)) return true;
		double y = playerLoc.getY();
		playerLoc.add(0, 1, 0);
		if(!playerLoc.getBlock().getType().equals(Material.AIR) && !playerLoc.add(0, 1, 0).getBlock().getType().equals(Material.AIR)){
			playerLoc.add(0, -1, 0);
			if(loc.getBlock().getType() != Material.AIR && !playerLoc.getBlock().equals(loc.getBlock())){
				playerLoc.add(0, 1, 0);
				if(loc.getBlock().equals(playerLoc.getBlock()) && playerLoc.add(0, 1, 0).getBlock().equals(Material.AIR)){
					playerLoc = loc;
					return true;
				}
				playerLoc.setY(y);
				if(banPlayer){
					player.kickPlayer("You are banned, Vclip is not allowed!");
					player.setBanned(true);
					Bukkit.banIP(player.getAddress().getAddress().getHostAddress());
				}
				return false;
			}
		}
		playerLoc = loc;
		return true;
	}
	
	private boolean isLocation(Location loc){
		if(loc.getX() == playerLoc.getX() && loc.getY() == playerLoc.getY() && loc.getZ() == playerLoc.getZ()){
			return true;
		}
		return false;
	}
	
	
}
