package me.specifies.MoreFishHook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.elsiff.morefish.MoreFish;

public class MoreFishHook extends JavaPlugin {
	
	private static MoreFish plugin;
	
	public void onEnable() {
		// Get the active plugin
		Plugin morefish = Bukkit.getPluginManager().getPlugin("MoreFish");
		
		// note that if an implementation is put into place we need to place enable priority under morefish
		// and then ensure that morefish enabled properly. If not, errors could be caused. 

		plugin = (MoreFish) morefish;
		new RegisterPlaceholders().register();
		
	}
	
	public void onDisable() {
		plugin = null;
	}
	
	public static MoreFish getInstance() {
		return plugin;
	}

}
