package tk.absolutesgamers.medic.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class OnSignChange implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("hunger.sign.create")) {
			if(event.getLine(0).equalsIgnoreCase("[Fed]")) {
				event.setLine(0, ChatColor.DARK_RED + "[Fed]");
			}
		}
		
		if(player.hasPermission("life.sign.create")) {
			if(event.getLine(0).equalsIgnoreCase("[Health]")) {
				event.setLine(0, ChatColor.DARK_RED + "[Health]");
			}
		}
	}
}
