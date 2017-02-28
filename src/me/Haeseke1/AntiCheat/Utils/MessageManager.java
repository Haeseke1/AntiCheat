package me.Haeseke1.AntiCheat.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Haeseke1.AntiCheat.Main.Main;

public class MessageManager {

	public static void sendAlertMessage(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + "&4 " + message));
	}
	
	public static void sendRemarkMessage(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + "&2 " + message));
	}
	
	public static void sendInfoMessage(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + "&e " + message));
	}
	
	public static void sendAlertMessage(String message,Player player){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + "&4 " + message));
	}
	
	public static void sendRemarkMessage(String message,Player player){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + "&2 " + message));
	}
	
	public static void sendInfoMessage(String message,Player player){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + "&e " + message));
	}
	
	public static void sendMessage(String message, Player player){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + " "+  message));
	}
	
	public static void sendMessage(String message, CommandSender sender){
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.logo + " "+  message));
	}
	
}
