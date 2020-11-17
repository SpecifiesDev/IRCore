package me.specifies.IRC;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.elsiff.morefish.MoreFish;
import me.specifies.IRC.Commands.ReloadConf;
import me.specifies.IRC.Events.ExecuteCustomCommands;
import me.specifies.IRC.Expansions.CustomCommand;
import me.specifies.IRC.Expansions.MoreFishExpansion;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	private static MoreFish morefish;
	private static Main instance;
	
	private HashMap<String, CustomCommand> commands = new HashMap<>();
	
	public void onEnable() {
		
		instance = this;
		
		this.saveDefaultConfig();
		
		// get an instance of the hook
		Plugin pl = Bukkit.getPluginManager().getPlugin("MoreFish");
		
		// make sure the plugin is enabled
		if(Bukkit.getPluginManager().isPluginEnabled(pl)) {
			
			// cast the plugin
			morefish = (MoreFish) pl;
			
			// register the expansion
			new MoreFishExpansion().register();
			
		} else Bukkit.getLogger().log(Level.WARNING, "[IRCore] Unable to hook into MoreFish, expansions will not be registered.");
		
		registerCommands();
		registerEvents();
		
		pullCustomCommands();
		
	}
	
	public void onDisable() {
		
		instance = null;
		morefish = null;
		
	}
	
	public static MoreFish getFish() {
		return morefish;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public String color(String m) {
		return ChatColor.translateAlternateColorCodes('&', m);
	}
	
	public HashMap<String, CustomCommand> getCommands() {
		return commands;
	}
	
	public void reload() {
		this.reloadConfig();
		
		commands = new HashMap<>();
		
		pullCustomCommands();
	}
	
	private void pullCustomCommands() {
		
		for(String key : this.getConfig().getConfigurationSection("commands").getKeys(false)) {
			
			// alias is key
			String execute = this.getConfig().getString("commands." + key + ".execute");
			String perm = this.getConfig().getString("commands." + key + ".permission");
			boolean player = this.getConfig().getBoolean("commands." + key + ".player");
			
			if(execute == null || perm == null) return;
			
			commands.put(key, new CustomCommand(execute, perm, player));
			
		}
		
	}
	
	private void registerCommands() {
		getCommand("ircreload").setExecutor(new ReloadConf());
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ExecuteCustomCommands(), this);
	}
	
}
