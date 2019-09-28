package me.thegrowl.xsuicide;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	// If player has permission-node xsuicide.self, they can use /kill to commit suicide
	// If player has permission-node xsuicide.others, they can use /kill <player-name> to kill others
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("kill")) {
			if (sender.hasPermission("xsuicide.self")) {
				// kill
				// kill <player-name>
				if (args.length == 0) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						p.setHealth(0);
						p.sendMessage(ChatColor.BOLD + "You have killed yourself");
						return true;
					}
					sender.sendMessage(ChatColor.DARK_RED + "Try /kill <player-name>");
				}
				if (sender.hasPermission("xsuicide.others")) {
					if (args.length >= 1) {
						if (Bukkit.getPlayerExact(args[0]) != null) {
							Player target = Bukkit.getPlayer(args[0]);
							target.setHealth(0);
							target.sendMessage(ChatColor.DARK_RED + "You have been killed");
							sender.sendMessage(ChatColor.DARK_GREEN + "You have killed " + target.getName());
							return true;
							
						}
						sender.sendMessage(ChatColor.DARK_RED + "Player not found - Make sure you are spelling correctly and that the player is online");
					}
					
				}
				
				
				
			}
			sender.sendMessage(ChatColor.DARK_RED + "");
			return true;
		}
		
		return false;
	}

}
