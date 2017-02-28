package me.Haeseke1.AntiCheat.Schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.Haeseke1.AntiCheat.Events.ChatEvent;

public class Timer extends BukkitRunnable{
	
	@Override
	public void run() {
		for(Player player: Bukkit.getOnlinePlayers()){
			if(ChatEvent.timers.containsKey(player.getUniqueId())){
				ChatEvent.timers.replace(player.getUniqueId(), ChatEvent.timers.get(player.getUniqueId()) - 1); 
				if(ChatEvent.timers.get(player.getUniqueId()) == 0){
				ChatEvent.timers.remove(player.getUniqueId());
				ChatEvent.players.remove(player.getUniqueId());
				}
			}
		}
	}

}
