package me.Haeseke1.AntiCheat.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.Haeseke1.AntiCheat.Main.Main;
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
	
	public static boolean anti_command_spam;
	
	public static int Max_Count;
	public static int Time;
	public static boolean canKick;
	

	public List<Player_Command> players = new ArrayList<>();
	
	@EventHandler
	public void spamCommand(PlayerCommandPreprocessEvent event) {
		if(event.getPlayer().hasPermission("AntiCheat.spam")) return;
		if(!anti_command_spam) return;
		Player player = event.getPlayer();
		for(Player_Command pc : players){
			if(pc.isPlayer(player.getUniqueId()))
				if(pc.addCount(player)){
					event.setCancelled(true);
					MessageManager.sendMessage("&cYou are using commands to fast!", player);
				}
				return;
		}
		new Player_Command(player.getUniqueId());
	}
	
	
	private class Player_Command {

		private UUID player;
		private int count = 1;

		public Player_Command(UUID player) {
			this.player = player;
			players.add(this);
			startTimer();
		}

		private void startTimer() {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

				@Override
				public void run() {
					remove();
				}

			}, Time * 20);
		}

		public boolean isPlayer(UUID uuid) {
			return this.player.equals(uuid);
		}

		public boolean addCount(Player player) {
			count++;
			return isOverLimit(player);
		}

		private boolean isOverLimit(Player player) {
			if (count > Max_Count) {
				if (canKick) {
					player.kickPlayer("Abuse of the command system");
				}
				return true;
			}
			return false;
		}

		private void remove() {
			players.remove(this);
		}
	}
	
	
	/*
	 *  End Anti Command Spam Module
	 */
}
