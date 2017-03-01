package me.Haeseke1.AntiCheat.Main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Haeseke1.AntiCheat.Commands.AntiCheat;
import me.Haeseke1.AntiCheat.Events.Chat;
import me.Haeseke1.AntiCheat.Events.PreProcess;
import me.Haeseke1.AntiCheat.Utils.ConfigManager;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class Main extends JavaPlugin{
	
	public static PluginManager pm;
	
	public static String logo;

	public static FileConfiguration Config;
	public static FileConfiguration CommandsConfig;
	

	
	@Override
	public void onEnable(){
		pm = Bukkit.getPluginManager();
		registerEvents();
		registerCommands();
		registerSchedulers();
		checkConfig();
		MessageManager.sendRemarkMessage("The plugin has been enabled");
	}
	
	@Override 
	public void onDisable(){
		pm = null;
	}
	
	public void registerEvents(){
		pm.registerEvents(new PreProcess(), this);
		pm.registerEvents(new Chat(), this);
	}
	
	public void registerSchedulers(){
		
	}
	
	public void registerCommands(){
		getCommand("AntiCheat").setExecutor(new AntiCheat());
	}
	
	
	public static void checkConfig(){
		getPlugin().saveDefaultConfig();
		getPlugin().reloadConfig();
		Config = ConfigManager.loadConfig("config");
		logo = ConfigManager.getString("logo");
		
		/*
		 * Block commands Module
		 */
		PreProcess.command_block = ConfigManager.getBoolean("command-block-module.command-block");
		MessageManager.sendInfoMessage("command_block = " + PreProcess.command_block);
		CommandsConfig = ConfigManager.loadConfig("commands");
		
		/*
		 * Anti command spam module
		 */
		PreProcess.anti_command_spam = ConfigManager.getBoolean("anti-command-spam-module.anti-command-spam");
		MessageManager.sendInfoMessage("anti_command_spam = " + PreProcess.anti_command_spam);
		PreProcess.Time = ConfigManager.getInt("anti-command-spam-module.time-interval-commands");
		PreProcess.Max_Count = ConfigManager.getInt("anti-command-spam-module.max-amount-commands");
		PreProcess.canKick = ConfigManager.getBoolean("anti-command-spam-module.kick-player-over-limit");
		/*
		 * Anti spam module
		 */
		Chat.anti_spam = ConfigManager.getBoolean("anti-spam-module.anti-spam");
		MessageManager.sendInfoMessage("anti_spam = " + Chat.anti_spam);
		Chat.time_interval_messages = ConfigManager.getInt("anti-spam-module.time-interval-messages");
		Chat.max_amount_messages = ConfigManager.getInt("anti-spam-module.max-amount-messages");
		Chat.max_amount_messages = ConfigManager.getInt("anti-spam-module.max-amount-messages");
		Chat.canKick = ConfigManager.getBoolean("anti-spam-module.kick-player-over-limit");
	}
	
	public static Main getPlugin(){
		return Main.getPlugin(Main.class);
	}
	
}
