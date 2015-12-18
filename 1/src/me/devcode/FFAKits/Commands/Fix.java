package me.devcode.FFAKits.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class Fix implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		if(args.length == 0) {
			Player p = (Player) s;
			p.teleport(p);
			p.sendMessage(FFA.ffam.prefix + "§3Du hast dich gefixed!");
		}else {
			Player p = (Player) s;
			if(!p.hasPermission("ffa.moderator")) {
				p.sendMessage(FFA.ffam.perm);
				return true;
			}
			if(Bukkit.getPlayer(args[0]) != null) {
			Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[0]));
			Bukkit.getPlayer(args[0]).sendMessage(FFA.ffam.prefix + "§3Du wurdest von §e" + p.getName() + " §3gefixed!");
			p.sendMessage(FFA.ffam.prefix + "§3Du hast den Spieler §e" + args[0] + " §3gefixed!");
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDer Spieler ist nicht online!");
			}
		}
		return true;
	}

}
