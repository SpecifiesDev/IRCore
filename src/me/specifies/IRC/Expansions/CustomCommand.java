package me.specifies.IRC.Expansions;

public class CustomCommand {
	
	private final String execute;
	private final String perm;
	private final boolean player;
	
	public CustomCommand(String execute, String perm, boolean player) {
		this.execute = execute;
		this.perm = perm;
		this.player = player;
	}
	
	public String getCommand() {
		return execute;
	}
	
	public String getPermission() {
		return perm;
	}
	
	public int getType() {
		return player ? 1 : 0;
	}
	

}
