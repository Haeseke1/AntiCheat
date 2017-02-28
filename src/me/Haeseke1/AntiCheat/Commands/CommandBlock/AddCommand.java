package me.Haeseke1.AntiCheat.Commands.CommandBlock;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.CommandSender;

import me.Haeseke1.AntiCheat.Main.Main;
import me.Haeseke1.AntiCheat.Utils.MessageManager;

public class AddCommand {

	public static boolean onCommand(CommandSender sender,String[] args){
		String command = args[2].toLowerCase();
		List<String> blockcommands = Main.CommandsConfig.getStringList("Commands");
		if(!blockcommands.contains(command)){
		blockcommands.add(command);
		Main.CommandsConfig.set("Commands", blockcommands);
		try {
			Main.CommandsConfig.save(new File(Main.getPlugin().getDataFolder(),"commands.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		MessageManager.sendMessage("&2Successfully added the &6" + command + "&2 command to the config", sender);
		return true;
		}
		MessageManager.sendMessage("&cThis command has already been added to the config", sender);
		return false;
	}
	
}
