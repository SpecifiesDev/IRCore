package me.specifies.IRC.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.specifies.IRC.Main;

public class ReloadConf implements CommandExecutor {
	
	private Main plugin;
	public ReloadConf() {
		this.plugin = Main.getInstance();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(p.hasPermission("irc.reload")) {
				plugin.reload();
				p.sendMessage(plugin.color("&aIRCore configuration has been reloaded."));
			} else p.sendMessage(plugin.color("&cYou do not have permission to execute this command."));
			
		} else {
			plugin.reload();
			sender.sendMessage(plugin.color("&aIRCore configuration has been reloaded."));
		}
		
		return true;
	}

}
