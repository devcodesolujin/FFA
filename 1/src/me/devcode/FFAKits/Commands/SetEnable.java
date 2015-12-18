package me.devcode.FFAKits.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class SetEnable implements CommandExecutor{
	
	
	//Not Supported
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		
		Player p = (Player) s;
		if(!p.hasPermission("1vs1.admin")) {
			p.sendMessage(FFA.ffam.perm);
			return true;
		}
		
		FFA.plugin.onCRegister();
		FFA.plugin.onRegister();
		FFA.plugin.loadMySQL();
		p.sendMessage(FFA.ffam.prefix + "§cPlugin enabled!");
	return true;	
	}

}
