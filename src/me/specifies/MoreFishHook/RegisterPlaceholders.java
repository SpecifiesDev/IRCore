package me.specifies.MoreFishHook;

import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.elsiff.morefish.MoreFish;
import me.elsiff.morefish.fishing.competition.FishingCompetition;

public class RegisterPlaceholders extends PlaceholderExpansion {
	
	private MoreFish hook;
	public RegisterPlaceholders() {
		this.hook = MoreFishHook.getInstance();
	}
	
	@Override
	public boolean canRegister() {
		return true;
	}
	
	/**
	 * Upon specification we should parse the plugin.yml for these values, so if finalized we need to do so
	 */
	@Override
	public String getAuthor() {
		return "SpecifiesDev";
	}
	
	@Override
	public String getIdentifier() {
		return "morefish";
	}
	
	@Override
	public String getVersion() {
		return "1.0";
	}
	
	@Override
	public String onRequest(OfflinePlayer p, String id) {
		FishingCompetition comp = hook.getCompetition();
		
		if(id.contains("top_player_")) {
			
			int target = Integer.parseInt(id.replace("top_player_", ""));
			
			if(comp.getRanking().size() >= target) return comp.recordOf(target).getFisher().getName();
			else return "No One";
			
		}
		
		if(id.contains("top_fish_length_")) {
			
			int target = Integer.parseInt(id.replace("top_fish_length_", ""));
			
			if(comp.getRanking().size() >= target) return Double.toString(comp.recordOf(target).getFish().getLength());
			else return "0.0";
				
		}
		
		if(id.contains("top_fish_")) {
			
			int target = Integer.parseInt(id.replace("top_fish_", ""));
			
			if(comp.getRanking().size() >= target) return comp.recordOf(target).getFish().getType().getName();
			else return "None";
		}
		
		if(id.equals("rank")) {
			// throw an error in the console alerting the user that it requires a player object
			if(p == null) throw new IllegalArgumentException("The rank placeholder requires a player.");
			
			if(comp.containsContestant(p)) return Integer.toString(comp.rankNumberOf(comp.recordOf(p)));
			else return "0";
			
		}
		
		if(id.equals("fish_length")) {
			if(p == null) throw new IllegalArgumentException("The fish_length placeholder requires a player.");
			
			if(comp.containsContestant(p)) return Double.toString(comp.recordOf(p).getFish().getLength());
			else return "0.0";
			
		}
		
		if(id.equals("fish")) {
			if(p == null) throw new IllegalArgumentException("The fish placeholder requires a player.");
			
			if(comp.containsContestant(p)) return comp.recordOf(p).getFish().getType().getName();
			else return "None";
		
		}
		
		
		
		return null;
	}

}
