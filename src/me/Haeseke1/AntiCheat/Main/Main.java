package me.Haeseke1.AntiCheat.Main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Haeseke1.AntiCheat.Commands.AntiCheat;
import me.Haeseke1.AntiCheat.Events.ChatEvent;
import me.Haeseke1.AntiCheat.Events.PreProcess;
import me.Haeseke1.AntiCheat.Schedulers.Timer;
import me.Haeseke1.AntiCheat.Utils.ConfigManager;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class Main extends JavaPlugin{
	
	public static PluginManager pm;
	
	public static boolean command_block;
	public static boolean anti_spam;
	
	public static String logo;

	public static FileConfiguration Config;
	public static FileConfiguration CommandsConfig;
	
	public static int time_interval_messages;
	public static int max_amount_messages;
	
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
		pm.registerEvents(new ChatEvent(), this);
	}
	
	public void registerSchedulers(){
		new Timer().runTaskTimer(this, 0L, 20L);
	}
	
	public void registerCommands(){
		getCommand("AntiCheat").setExecutor(new AntiCheat());
	}
	
	public void checkConfig(){
		Config = ConfigManager.loadConfig("config");
		logo = ConfigManager.getString("logo");
		command_block = ConfigManager.getBoolean("command-block");
		MessageManager.sendInfoMessage("command_block = " + command_block);
		anti_spam = ConfigManager.getBoolean("anti-spam");
		MessageManager.sendInfoMessage("anti_spam = " + anti_spam);
	    CommandsConfig = ConfigManager.loadConfig("commands");
	    time_interval_messages = ConfigManager.getInt("time-interval-messages");
	    max_amount_messages = ConfigManager.getInt("max-amount-messages");
	}
	
	public static Main getPlugin(){
		return Main.getPlugin(Main.class);
	}
	
}
