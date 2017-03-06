package me.Haeseke1.AntiCheat.Events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.Haeseke1.AntiCheat.Main.Main;
import me.Haeseke1.AntiCheat.Module.Anti_Command_Spam;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class PreProcess implements Listener {

	
	/*
	 *  Block Command Module
	 */
	public static boolean command_block;
	
	@EventHandler
	public void blockCommand(PlayerCommandPreprocessEvent event) {
		if (command_block){
			String command = event.getMessage().substring(1);
			List<String> blockedcommands = Main.CommandsConfig.getStringList("Commands");
			if (!event.getPlayer().hasPermission("anticheat.staff.command")) {
				for (String blockedcommand : blockedcommands) {
					if (command.contains(blockedcommand.toLowerCase())) {
						MessageManager.sendMessage("&cThis command has been blocked", event.getPlayer());
						event.setCancelled(true);
						return;
					}
				}
			}
		}
	}
	/*
	 *  End Block Command Module
	 */
	
	
	
	/*
	 *  Anti Command Spam Module
	 */
	
	@EventHandler
	public void spamCommand(PlayerCommandPreprocessEvent event) {
		if(event.getPlayer().hasPermission("AntiCheat.spam")) return;
		if(!Anti_Command_Spam.anti_command_spam) return;
		Player player = event.getPlayer();
		for(Anti_Command_Spam pc : Anti_Command_Spam.players){
			if(pc.isPlayer(player.getUniqueId()))
				if(pc.addCount(player)){
					event.setCancelled(true);
					MessageManager.sendMessage("&cYou are using commands to fast!", player);
				}
				return;
		}
		new Anti_Command_Spam(player.getUniqueId());
	}
	
	/*
	 *  End Anti Command Spam Module
	 */
}
