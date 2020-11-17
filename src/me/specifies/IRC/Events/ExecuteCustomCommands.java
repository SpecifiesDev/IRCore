package me.specifies.IRC.Events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.specifies.IRC.Main;
import me.specifies.IRC.Expansions.CustomCommand;

public class ExecuteCustomCommands implements Listener {
	
	private Main plugin;
	public ExecuteCustomCommands() {
		this.plugin = Main.getInstance();
	}
	
	@EventHandler
	public void execute(PlayerCommandPreprocessEvent e) {
		String command = e.getMessage().split(" ")[0].replaceAll("/", "");
		
		
		if(command == null) return;
		
		HashMap<String, CustomCommand> commands = plugin.getCommands();
		
		if(!(commands.containsKey(command))) return;
		
		e.setCancelled(true);
		
		CustomCommand comm = commands.get(command);
		
		Player p = e.getPlayer();
		
		// player
		if(comm.getType() == 1) {
			
			if(p.hasPermission(comm.getPermission())) {
				
				p.performCommand(comm.getCommand());
				
				
			} else p.sendMessage(plugin.color("&cYou do not have permission to use this command."));
			
		} else {
			
			if(p.hasPermission(comm.getPermission())) {
				
				Bukkit.getConsoleSender().getServer().dispatchCommand(Bukkit.getConsoleSender(), comm.getCommand());
				
			} else p.sendMessage(plugin.color("&cYou do not have permission to use this command."));
			
		}
	}
}
