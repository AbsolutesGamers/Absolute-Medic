package tk.absolutesgamers.medic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.absolutesgamers.medic.managers.FilesManager;

public class Life implements CommandExecutor {
	
	private FilesManager files = FilesManager.load();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			if(command.getName().equalsIgnoreCase("life")) {
				if(args.length == 0) {
					sender.sendMessage(files.getTranlation().getString("life.console.invalidCommand").replaceAll("&", "§"));
					return true;
				}
				
				Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
				
				if((args.length == 1) && (targetPlayer != null)) {
					Double maxHealth = targetPlayer.getMaxHealth();
					targetPlayer.setHealth(maxHealth);
					sender.sendMessage(files.getTranlation().getString("life.console.sender.sucess").replaceAll("&", "§").replaceAll("%p", targetPlayer.getName()));
					targetPlayer.sendMessage(files.getTranlation().getString("life.console.target.sucess").replaceAll("&", "§").replaceAll("%p", sender.getName()));
					return true;
				}
				if((args.length == 1) && (targetPlayer == null)) {
					sender.sendMessage(files.getTranlation().getString("life.console.sender.invalidPlayer").replaceAll("&", "§").replaceAll("%p", args[0]));
					return true;
				}
				if(args.length > 1) {
					sender.sendMessage(files.getTranlation().getString("life.console.invalidCommand").replaceAll("&", "§"));
					return true;
				}
			}
		}
		
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("life")) {
			if((args.length == 0) && (player.hasPermission("life.sender"))) {
				Double maxHealth = player.getMaxHealth();
				player.setHealth(maxHealth);
				player.sendMessage(files.getTranlation().getString("life.player.sender.sucess.yourself").replaceAll("&", "§"));
				return true;
			}
			if((args.length == 0) && (!player.hasPermission("life.sender"))) {
				player.sendMessage(files.getTranlation().getString("life.player.sender.noPermission.yourself"));
				return true;
			}
			
			Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
			
			if((args.length == 1) && (targetPlayer != null) && (player.hasPermission("life.target"))) {
				Double maxHealth = targetPlayer.getMaxHealth();
				targetPlayer.setHealth(maxHealth);
				sender.sendMessage(files.getTranlation().getString("life.player.sender.sucess.other").replaceAll("&", "§").replaceAll("%p", targetPlayer.getName()));
				targetPlayer.sendMessage(files.getTranlation().getString("life.player.target.sucess").replaceAll("&", "§").replaceAll("%p", sender.getName()));
				return true;
			}
			if((args.length == 1) && (targetPlayer != null) && (!player.hasPermission("life.target"))) {
				player.sendMessage(files.getTranlation().getString("life.player.sender.noPermission.other").replaceAll("&", "§"));
				return true;
			}
			if((args.length == 1) && (targetPlayer == null)) {
				player.sendMessage(files.getTranlation().getString("life.player.sender.invalidPlayer").replaceAll("&", "§").replaceAll("%p", args[0]));
				return true;
			}
			if(args.length > 1) {
				player.sendMessage(files.getTranlation().getString("life.player.invalidCommand").replaceAll("&", "§"));
				return true;
			}
		}
		return false;
	}
}
