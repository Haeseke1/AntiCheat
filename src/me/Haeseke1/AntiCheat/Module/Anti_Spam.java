package me.Haeseke1.AntiCheat.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Haeseke1.AntiCheat.Main.Main;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class Anti_Spam {
	
	public static List<Anti_Spam> players = new ArrayList<>();
	
	public static boolean anti_spam;
	
	public static int time_interval_messages;
	public static int max_amount_messages;
	
	public static boolean canKick;
	
	
	
	private UUID player;
	private int count = 1;
	private String message;

	public Anti_Spam(UUID player, String message) {
		this.player = player;
		this.message = message;
		players.add(this);
		startTimer();
	}

	private void startTimer() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				remove();
			}

		}, time_interval_messages * 20);
	}

	public boolean isPlayer(UUID uuid) {
		return this.player.equals(uuid);
	}
	
	public boolean isMessage(String message) {
		if(this.message.contains(message) || this.message.equalsIgnoreCase(message) || message.contains(this.message)){
			return true;
		}
		this.message = message;
		return false;
	}
	
	public boolean addCount(Player player) {
		count++;
		return isOverLimit(player);
	}

	private boolean isOverLimit(Player player) {
		if (count > max_amount_messages) {
			if (canKick) {
				player.kickPlayer("Abuse of the message system");
			}
			if(time_interval_messages < count / 3){
				player.kickPlayer("Abuse of the message system");
				MessageManager.sendAlertMessage("Server protection mode engaged!");
				MessageManager.sendAlertMessage("Kicked " + player.getName());
				MessageManager.sendAlertMessage("Reason: Chat abuse, at impossible speed rate!");
			}
			return true;
		}
		return false;
	}

	private void remove() {
		players.remove(this);
	}
	
}
