package tk.absolutesgamers.medic.events;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import tk.absolutesgamers.medic.managers.FilesManager;

public class OnPlayerInteract implements Listener {
	
	private FilesManager files = FilesManager.load();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getState() instanceof Sign) {
				Player player = event.getPlayer();
				Sign sign = (Sign) event.getClickedBlock().getState();
				if(sign.getLine(0).equalsIgnoreCase("§4[Fed]")) {
					if(player.hasPermission("hunger.sign.use")) {
						player.setFoodLevel(20);
						player.sendMessage(files.getTranlation().getString("hunger.sign.sucess"));
					}
				}
				
				if(sign.getLine(0).equalsIgnoreCase("§4[Health]")) {
					if(player.hasPermission("life.sign.use")) {
						Double maxHealth = player.getMaxHealth();
						player.setHealth(maxHealth);
						player.sendMessage(files.getTranlation().getString("life.sign.sucess"));
					}
				}
			}
		}
	}
}
