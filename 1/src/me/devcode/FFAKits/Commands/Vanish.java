package me.devcode.FFAKits.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class Vanish implements CommandExecutor{
	
	public static ArrayList<String> vanish = new ArrayList<String>();
	
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		Player p = (Player) s;
		if(!p.hasPermission("ffa.moderator")) {
			p.sendMessage(FFA.ffam.perm);
			return true;
		}
		if(args.length == 0) {
		
			if(!vanish.contains(p.getName())) {
			p.sendMessage(FFA.ffam.prefix + "§3Du bist nun im Vanish!");
			vanish.add(p.getName());
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!all.hasPermission("ffa.moderator")) {
				all.hidePlayer(p);
				}
			}
			}else{
				p.sendMessage(FFA.ffam.prefix + "§3Du bist nun nicht mehr im Vanish!");
				vanish.remove(p.getName());
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.showPlayer(p);
				}
			}
		}else {
			
			if(Bukkit.getPlayer(args[0]) != null) {
			if(!vanish.contains(args[0])) {
			Bukkit.getPlayer(args[0]).sendMessage(FFA.ffam.prefix + "§3Du wurdest von §e" + p.getName() + " §3in den Vanish gesetzt!");
			p.sendMessage(FFA.ffam.prefix + "§3Du hast den Spieler §e" + args[0] + " §3in den Vanish gesetzt!");
			vanish.add(args[0]);
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!all.hasPermission("ffa.moderator")) {
					all.hidePlayer(Bukkit.getPlayer(args[0]));
					}
				
			}
			}else{
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.showPlayer(Bukkit.getPlayer(args[0]));
				}
				Bukkit.getPlayer(args[0]).sendMessage(FFA.ffam.prefix + "§3Du wurdest von §e" + p.getName() + " §3aus dem Vanish entfernt!");
				p.sendMessage(FFA.ffam.prefix + "§3Du hast den Spieler §e" + args[0] + " §3aus dem Vanish entfernt!");
				vanish.remove(args[0]);
			}
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDer Spieler ist nicht online!");
			}
		}
		return true;
	}

}
