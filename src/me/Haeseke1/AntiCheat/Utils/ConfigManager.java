package me.Haeseke1.AntiCheat.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Haeseke1.AntiCheat.Main.Main;

public class ConfigManager {

	public static FileConfiguration config = Main.getPlugin().getConfig();
	
	public static FileConfiguration loadConfig(String configname){
		File file = new File(Main.getPlugin().getDataFolder(),configname + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		if(file.exists()){
		try {
			InputStreamReader reader = new InputStreamReader(Main.getPlugin().getResource(file.getName()),"UTF-8");
			YamlConfiguration readedconfig = YamlConfiguration.loadConfiguration(reader);
			config.setDefaults(readedconfig);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		}else{
			Main.getPlugin().saveResource(file.getName(), true);
			config = YamlConfiguration.loadConfiguration(file);
		}
		return config;
	}
	
	public static boolean getBoolean(String path){
		if(config.get(path) == null){
			resetConfig(); 
			return false;
		}
		return config.getBoolean(path);
	}
	
	public static String getString(String path){
		if(config.get(path) == null){
			resetConfig(); 
			return null;
		}
		return config.getString(path);
	}
	
	public static int getInt(String path){
		if(config.get(path) == null){
			resetConfig(); 
			return 0;
		}
		return config.getInt(path);
	}
	
	public static void set(FileConfiguration config,String path,Object obj){
		config.set(path, obj);
		Main.getPlugin().saveConfig();
	}
	
	public static void resetConfig(){
		Main.pm.disablePlugin(Main.getPlugin());
		config.options().copyDefaults(true);
		MessageManager.sendAlertMessage("The plugin has been disabled due a problem with the configs");
		Main.getPlugin().saveConfig();
	}
	
	public static void saveConfigFile(String name){
		File file = new File(Main.getPlugin().getDataFolder(),name + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
