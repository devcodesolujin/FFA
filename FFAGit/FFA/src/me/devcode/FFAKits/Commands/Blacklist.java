package me.devcode.FFAKits.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class Blacklist implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		Player p = (Player) s;
		if(!p.hasPermission("mlg.blacklist")) {
			p.sendMessage(FFA.ffam.perm);
			return true;
		}
		if(args.length < 2 && !args[0].equalsIgnoreCase("list")) {
			p.sendMessage(FFA.ffam.prefix + "§cFehler! /blacklist <add|remove> <Wort>");
			return true;
		}
		if(args[0].equalsIgnoreCase("remove")) {
			List<String> blacklist = FFA.plugin.getConfig().getStringList("Blacklist");
			if(blacklist.contains(args[1])) {
				blacklist.remove(args[1]);
				FFA.plugin.getConfig().set("Blacklist", blacklist);
				FFA.plugin.saveConfig();
				p.sendMessage(FFA.ffam.prefix + "§3Das Wort: §e" + args[1] + " §3wurde aus der Blacklist entfernt!");
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDas Wort: §e" + args[1] + " §cist nicht in der Blacklist!");
			}
			return true;
		}
		
		if(args[0].equalsIgnoreCase("add")) {
			List<String> blacklist = FFA.plugin.getConfig().getStringList("Blacklist");
			
				blacklist.add(args[1]);
				FFA.plugin.getConfig().set("Blacklist", blacklist);
				FFA.plugin.saveConfig();
				p.sendMessage(FFA.ffam.prefix + "§3Das Wort: §e" + args[1] + " §3wurde zu der Blacklist hinzugefügt!");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("list")) {
			
			List<String> blacklist = FFA.plugin.getConfig().getStringList("Blacklist");
			p.sendMessage(FFA.ffam.prefix + "§3Blacklist Wörter: §e" + blacklist.toString());
			return true;
		}
		
	return true;	
	}

}
