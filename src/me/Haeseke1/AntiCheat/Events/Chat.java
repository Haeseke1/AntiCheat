package me.Haeseke1.AntiCheat.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.Haeseke1.AntiCheat.Module.Anti_Spam;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

@SuppressWarnings("deprecation")
public class Chat implements Listener{
	
	/*
	 * Anti Spam Module
	 */
	
	
	@EventHandler
	private void playerChat(PlayerChatEvent event){
		if(event.getPlayer().hasPermission("AntiCheat.spam")) return;
		if(!Anti_Spam.anti_spam) return;
		
		Player player = event.getPlayer();
		for(Anti_Spam pm : Anti_Spam.players){
			if(pm.isPlayer(player.getUniqueId())){
				if(pm.addCount(player)){
					MessageManager.sendMessage("&cYou are sending messages to fast!", player);
					event.setCancelled(true);
					return;
				}
				if(pm.isMessage(event.getMessage())){
					MessageManager.sendMessage("&cDon't write the same message as your previous one", player);
					event.setCancelled(true);
				}
				return;
			}
		}
		new Anti_Spam(player.getUniqueId(), event.getMessage());
	}
	
	/*
	 * End Anti Spam Module
	 */
}
