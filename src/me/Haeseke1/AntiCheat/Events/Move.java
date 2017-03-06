package me.Haeseke1.AntiCheat.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Haeseke1.AntiCheat.Module.Anti_Wall_Running;

public class Move implements Listener{
	
	
	@EventHandler
	private void wallRunning(PlayerMoveEvent event){
		if(!Anti_Wall_Running.anti_wall_running) return;
		Player player = event.getPlayer();
		for(Anti_Wall_Running awr : Anti_Wall_Running.players){
			if(awr.isPlayer(player.getUniqueId())){
				if(!awr.isPossible(player, event.getTo())){
					if(player != null){
						player.kickPlayer("You are kicked, Vclip is not allowed!");
					}
				}
				return;
			}
		}
		new Anti_Wall_Running(player.getUniqueId(), player.getLocation());
	}
	
	
	
	
}
