package me.Haeseke1.AntiCheat.Events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.Haeseke1.AntiCheat.Main.Main;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

@SuppressWarnings("deprecation")
public class ChatEvent implements Listener{

	public static HashMap<UUID,Integer> players = new HashMap<>();
	public static HashMap<UUID,Integer> timers = new HashMap<>();
	public static HashMap<UUID,String> previous_message = new HashMap<>();
	
	@EventHandler
	public void playerChat(PlayerChatEvent event){
		if(event.getPlayer().hasPermission("AntiCheat.spam")) return;
		if(!Main.anti_spam) return;
		Player player = event.getPlayer();
		if(!players.containsKey(player.getUniqueId())){
			players.put(player.getUniqueId(), 1);
			return;
		}
		int amount = players.get(player.getUniqueId());
		players.remove(player.getUniqueId());
		players.put(player.getUniqueId(), amount + 1);
		if(amount + 1 > Main.max_amount_messages){
			player.kickPlayer("Abuse of the chat system");
			players.remove(player.getUniqueId());
			timers.remove(player.getUniqueId());
			event.setCancelled(true);
		}
		if(!timers.containsKey(player.getUniqueId())){
			timers.put(player.getUniqueId(), Main.time_interval_messages);
		}
		if(!previous_message.containsKey(player.getUniqueId())){
			previous_message.put(player.getUniqueId(), event.getMessage());
			return;
		}
		if(previous_message.get(player.getUniqueId()).equalsIgnoreCase(event.getMessage())){
			MessageManager.sendMessage("&cDon't write the same message as your previous one", player);
			event.setCancelled(true);
			return;
		}
		previous_message.replace(player.getUniqueId(),event.getMessage());
	}
	
}
