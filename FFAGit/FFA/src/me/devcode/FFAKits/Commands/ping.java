package me.devcode.FFAKits.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class ping implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		if(args.length == 0) {
			Player p = (Player) s;
			
			p.sendMessage(FFA.ffam.prefix + "§3Dein Ping: §e" + ((CraftPlayer)p).getHandle().ping + "ms");
			
		}
		
		return true;
	}

}
