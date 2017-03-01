package me.Haeseke1.AntiCheat.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Haeseke1.AntiCheat.Commands.CommandBlock.AddCommand;
import me.Haeseke1.AntiCheat.Commands.Toggle.ToggleCommand;
import me.Haeseke1.AntiCheat.Utils.MessageManager;
import net.md_5.bungee.api.ChatColor;

public class AntiCheat implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (args.length == 0) {
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&c =*= &6A&8n&6t&8i&6C&8h&6e&8a&6t&c =*= "));
				MessageManager.sendMessage("&6/anticheat toggle [mode] [true/false] &e#Change the config settings",
						player);
				MessageManager.sendMessage("&6/anticheat commands add [command] &e#Add commands to the block list",
						player);
				return false;
			}

			if (args.length == 3 && args[0].equalsIgnoreCase("toggle")) {
				ToggleCommand.onCommand(sender, cmd, label, args);
				return false;
			}

			if (args.length == 3 && args[0].equalsIgnoreCase("commands") && args[1].equalsIgnoreCase("add")) {
				AddCommand.onCommand(sender, args);
				return false;
			}

		}
		return false;
	}
	
}
