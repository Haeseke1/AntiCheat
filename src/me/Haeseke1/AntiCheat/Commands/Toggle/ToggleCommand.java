package me.Haeseke1.AntiCheat.Commands.Toggle;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Haeseke1.AntiCheat.Main.Main;
import me.Haeseke1.AntiCheat.Utils.ConfigManager;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class ToggleCommand {

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		try{
		boolean statement = Boolean.getBoolean(args[2]);
		switch(args[1]){
		case "block-command":
			ConfigManager.set(Main.getPlugin().getConfig(), args[1], statement);
			Main.getPlugin().saveConfig();
			break;
		case "anti-spam":
			ConfigManager.set(Main.getPlugin().getConfig(), args[1], statement);
			Main.getPlugin().saveConfig();
            break;
		default:
			MessageManager.sendMessage("&4This mode doesn't exists in the config", sender);
			return false;
		}
		}catch(Exception e){
			MessageManager.sendMessage("&4This isn't a valid statement", sender);
		}
		return false;
	}
	
}
