package me.devcode.FFAKits.Commands;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.MySQLStats;

public class Stats implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] arg3) {
		if(!(s instanceof Player)) {
			
			s.sendMessage(FFA.ffam.noplayer );
			return true;
		}
		Player p = (Player) s;
		if(arg3.length == 0) {
		double KD = 0;
		if(MySQLStats.getDeaths(p.getUniqueId().toString()) != 0) {
			
		 KD = Double.valueOf(MySQLStats.getKills(p.getUniqueId().toString())) / Double.valueOf(MySQLStats.getDeaths(p.getUniqueId().toString()));
		}else{
			KD = Double.valueOf(MySQLStats.getKills(p.getUniqueId().toString())) / Double.valueOf(1);	
		}
		DecimalFormat f = new DecimalFormat("#0.00");
		double toFormat = ((double)Math.round(KD*100))/100;
		
		String kd = f.format(toFormat);
		p.sendMessage("§8§m---------------------------------------------------");
		p.sendMessage("§3Deine Kills: §e" + MySQLStats.getKills(p.getUniqueId().toString()));
		p.sendMessage("§3Deine Deaths: §e" + MySQLStats.getDeaths(p.getUniqueId().toString()));
		p.sendMessage("§3Deine K/D: §e" + kd.replace("Infinity", "0").replace("NaN", "0"));
		p.sendMessage("§3Deine Punkte: §e" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())));
		p.sendMessage("§3Dein Rang: §e" + MySQLStats.getOwnRank(p.getUniqueId().toString()));		
		p.sendMessage("§8§m---------------------------------------------------");
		return true;
		}else{
			double KD = 0;
			OfflinePlayer player = Bukkit.getOfflinePlayer(arg3[0]);
			if(MySQLStats.getDeaths(player.getUniqueId().toString()) != 0) {
				
			 KD = Double.valueOf(MySQLStats.getKills(player.getUniqueId().toString())) / Double.valueOf(MySQLStats.getDeaths(player.getUniqueId().toString()));
			}else{
				KD = Double.valueOf(MySQLStats.getKills(player.getUniqueId().toString())) / Double.valueOf(1);	
			}
			DecimalFormat f = new DecimalFormat("#0.00");
			double toFormat = ((double)Math.round(KD*100))/100;
			
			String kd = f.format(toFormat);
			p.sendMessage("§8§m---------------------------------------------------");
			p.sendMessage("§3Kills von §6"+ arg3[0] + ": §e" + MySQLStats.getKills(player.getUniqueId().toString()));
			p.sendMessage("§3Deaths von §6"+ arg3[0] + ": §e" + MySQLStats.getDeaths(player.getUniqueId().toString()));
			p.sendMessage("§3K/D von §6"+ arg3[0] +": §e" + kd.replace("Infinity", "0").replace("NaN", "0"));
			p.sendMessage("§3Punkte von §6"+ arg3[0] +": §e" + Math.round((float) MySQLStats.getPunkte(player.getUniqueId().toString())));
			p.sendMessage("§3Rang von §6"+ arg3[0] +": §e" + MySQLStats.getOwnRank(player.getUniqueId().toString()));
			p.sendMessage("§8§m---------------------------------------------------");
			return true;
		}
		
	}
}
