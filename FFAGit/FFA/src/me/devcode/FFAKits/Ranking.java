package me.devcode.FFAKits;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Ranking {
	
	public static HashMap<Integer, String> rang = new HashMap<Integer, String>();

		
		public static void setRanking(Player p) {
		ResultSet rs = MySQL.query("SELECT UUID FROM FFAStats ORDER BY KILLS DESC LIMIT 5");
		int in = 0;
		try{
			while(rs.next()) {
				in++;
				rang.put(in, rs.getString("UUID"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(rang.containsKey(1) && rang.containsKey(2) && rang.containsKey(3) && rang.containsKey(4) && rang.containsKey(5)) {
			for(int i = 0; i < rang.size(); i++) {
				int id = i+1;
			
			
				
					
						
						{
				
				String name = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(id))).getName();	
				if(name != null) {
			p.sendMessage("§8[§e" + id +"§8]§cName: §e" + name);
			OfflinePlayer p1 = Bukkit.getOfflinePlayer(name);
			int kills = MySQLStats.getKills(p1.getUniqueId().toString());
			p.sendMessage("§cKills: §e" + kills);
			p.sendMessage("");
				}
						}
			if(id == 10) {
				if(rang.containsKey(10)) {
				String name = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(id))).getName();		
				if(name != null) {
				p.sendMessage("§8[§e" + id +"§8]§cName: §e" + name);
				OfflinePlayer p1 = Bukkit.getOfflinePlayer(name);
				int kills = MySQLStats.getKills(p1.getUniqueId().toString());
				p.sendMessage("§cKills: §e" + kills);
				}
				}
			}
			
			}
			
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cEs gibt noch keine §eTop5 §cSpieler!");
			}
		}

}
