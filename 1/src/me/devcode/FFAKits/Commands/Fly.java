package me.devcode.FFAKits.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class Fly implements CommandExecutor{
	
	ArrayList<String> fly = new ArrayList<String>();
	
	
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
			if(!fly.contains(p.getName())) {
			p.setAllowFlight(true);
			p.setFlying(true);
			p.sendMessage(FFA.ffam.prefix + "§3Du kannst nun fliegen!");
			fly.add(p.getName());
			}else{
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage(FFA.ffam.prefix + "§3Du kannst nun nicht mehr fliegen!");
				fly.remove(p.getName());
			}
		}else {
			
			
			if(Bukkit.getPlayer(args[0]) != null) {
				if(fly.contains(args[0])) {
				Bukkit.getPlayer(args[0]).setAllowFlight(false);
				Bukkit.getPlayer(args[0]).setFlying(false);
			Bukkit.getPlayer(args[0]).sendMessage(FFA.ffam.prefix + "§3Du kannst nun nicht mehr fliegen!");
			p.sendMessage(FFA.ffam.prefix + "§3Der Spieler §e" + args[0] + " §3kann nun nicht mehr fliegen!");
				fly.remove(args[0]);
				}else{
					Bukkit.getPlayer(args[0]).setAllowFlight(true);
					Bukkit.getPlayer(args[0]).setFlying(true);
				Bukkit.getPlayer(args[0]).sendMessage(FFA.ffam.prefix + "§3Du kannst nun fliegen!");
				p.sendMessage(FFA.ffam.prefix + "§3Der Spieler §e" + args[0] + " §3kann nun fliegen!");
				fly.add(args[0]);
				}
				}else{
				p.sendMessage(FFA.ffam.prefix + "§cDer Spieler ist nicht online!");
			}
		}
		return true;
	}

}
