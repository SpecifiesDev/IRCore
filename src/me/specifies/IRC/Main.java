package me.specifies.IRC;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.elsiff.morefish.MoreFish;
import me.specifies.IRC.Expansions.MoreFishExpansion;

public class Main extends JavaPlugin {
	
	private static MoreFish morefish;
	private static Main instance;
	
	public void onEnable() {
		
		instance = this;
		
		// get an instance of the hook
		Plugin pl = Bukkit.getPluginManager().getPlugin("MoreFish");
		
		// make sure the plugin is enabled
		if(Bukkit.getPluginManager().isPluginEnabled(pl)) {
			
			// cast the plugin
			morefish = (MoreFish) pl;
			
			// register the expansion
			new MoreFishExpansion().register();
			
		} else Bukkit.getLogger().log(Level.WARNING, "[IRCore] Unable to hook into MoreFish, expansions will not be registered.");
		
		
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
	
}
