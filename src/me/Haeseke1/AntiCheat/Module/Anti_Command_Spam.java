package me.Haeseke1.AntiCheat.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Haeseke1.AntiCheat.Main.Main;

public class Anti_Command_Spam {
	
	public static boolean anti_command_spam;
	
	public static int Max_Count;
	public static int Time;
	public static boolean canKick;
	

	public static List<Anti_Command_Spam> players = new ArrayList<>();
	

	private UUID player;
	private int count = 1;

	public Anti_Command_Spam(UUID player) {
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
