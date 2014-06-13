package tk.absolutesgamers.medic;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import tk.absolutesgamers.medic.commands.Hunger;
import tk.absolutesgamers.medic.commands.Life;
import tk.absolutesgamers.medic.events.OnPlayerInteract;
import tk.absolutesgamers.medic.events.OnSignChange;
import tk.absolutesgamers.medic.managers.FilesManager;

public class Medic extends JavaPlugin {
	
	public void onEnable() {
		FilesManager.load().instalar(this);
		getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new OnSignChange(), this);
		getCommand("hunger").setExecutor(new Hunger());
		getCommand("life").setExecutor(new Life());
		getLogger().info("Plugin Activated!");
	}
	
	public void onDisable() {
		HandlerList.unregisterAll(this);
		getLogger().info("Plugin Desactivated!");
	}
}
