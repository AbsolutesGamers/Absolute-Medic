package tk.absolutesgamers.medic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.absolutesgamers.medic.managers.FilesManager;

public class Hunger implements CommandExecutor {
	
	private FilesManager files = FilesManager.load();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			if(command.getName().equalsIgnoreCase("hunger")) {
				if(args.length == 0) {
					sender.sendMessage(files.getTranlation().getString("hunger.console.invalidCommand").replaceAll("&", "§"));
					return true;
				}
				
				Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
				
				if((args.length == 1) && (targetPlayer != null)) {
					targetPlayer.setFoodLevel(20);
					sender.sendMessage(files.getTranlation().getString("hunger.console.sender.sucess").replaceAll("&", "§").replaceAll("%p", targetPlayer.getName()));
					targetPlayer.sendMessage(files.getTranlation().getString("hunger.console.target.sucess").replaceAll("&", "§").replaceAll("%p", sender.getName()));
					return true;
				}
				if((args.length == 1) && (targetPlayer == null)) {
					sender.sendMessage(files.getTranlation().getString("hunger.console.sender.invalidPlayer").replaceAll("&", "§").replaceAll("%p", args[0]));
					return true;
				}
				if(args.length > 1) {
					sender.sendMessage(files.getTranlation().getString("hunger.console.invalidCommand").replaceAll("&", "§"));
					return true;
				}
			}
		}
		
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("hunger")) {
			if((args.length == 0) && (player.hasPermission("hunger.sender"))) {
				player.setFoodLevel(20);
				player.sendMessage(files.getTranlation().getString("hunger.player.sender.sucess.yourself").replaceAll("&", "§"));
				return true;
			}
			if((args.length == 0) && (!player.hasPermission("hunger.sender"))) {
				player.sendMessage(files.getTranlation().getString("hunger.player.sender.noPermission.yourself").replaceAll("&", "§"));
				return true;
			}
			
			Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
			
			if((args.length == 1) && (targetPlayer != null) && (player.hasPermission("hunger.target"))) {
				player.setFoodLevel(20);
				sender.sendMessage(files.getTranlation().getString("hunger.player.sender.sucess.other").replaceAll("&", "§").replaceAll("%p", targetPlayer.getName()));
				targetPlayer.sendMessage(files.getTranlation().getString("hunger.player.target.sucess").replaceAll("&", "§").replaceAll("%p", sender.getName()));
				return true;
			}
			if((args.length == 1) && (targetPlayer != null) && (!player.hasPermission("hunger.target"))) {
				player.sendMessage(files.getTranlation().getString("hunger.player.sender.noPermission.other").replaceAll("&", "§"));
				return true;
			}
			if((args.length == 1) && (targetPlayer == null)) {
				player.sendMessage(files.getTranlation().getString("hunger.player.sender.invalidPlayer").replaceAll("&", "§").replaceAll("%p", args[0]));
				return true;
			}
			if(args.length > 1) {
				player.sendMessage(files.getTranlation().getString("hunger.player.invalidCommand").replaceAll("&", "§"));
				return true;
			}
		}
		return false;
	}
}
