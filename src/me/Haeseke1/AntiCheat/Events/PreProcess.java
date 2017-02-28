package me.Haeseke1.AntiCheat.Events;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.Haeseke1.AntiCheat.Main.Main;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class PreProcess implements Listener{

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event){
		if(!Main.command_block) return;
		String command = event.getMessage().substring(1);
		List<String> blockedcommands = Main.CommandsConfig.getStringList("Commands");
		if(!event.getPlayer().hasPermission("anticheat.staff.command")){
		for(String blockedcommand: blockedcommands){
			if(command.contains(blockedcommand.toLowerCase())){
				MessageManager.sendMessage("&cThis command has been blocked", event.getPlayer());
				event.setCancelled(true);
				return;
			}
		}
	  }
	}
	
}
