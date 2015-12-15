package me.devcode.FFAKits;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;



public class MySQLStats {
	
	public static boolean playerExists(String uuid) {
		try{
			ResultSet rs = MySQL.query("SELECT * FROM FFAStats WHERE UUID= '" + uuid + "'");
			if(rs.next()) {
				return rs.getString("UUID")!= null;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean playerVerbot(String uuid) {
		try{
			ResultSet rs = MySQL.query("SELECT * FROM FFAStats WHERE UUID= '" + uuid + "'");
			if(rs.next()) {
				return rs.getString("VERBOTUUID")!=null;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void setVerbot(String uuid, Long zeit) {
		if(playerExists(uuid)) {
		MySQL.update("UPDATE FFAStats Set UUID='" + uuid + "', VERBOT='" + zeit + "', VERBOTUUID='" + uuid + "'");
	}else{
		createPlayer(uuid);
		setVerbot(uuid, zeit);
	}
	}
	
	public static Long getVerbot(String uuid) {
		if(playerExists(uuid)) {
		ResultSet rs = MySQL.query("SELECT * FROM FFAStats WHERE UUID= '" + uuid + "'");
		try{
		if(rs.next()) {
			return rs.getLong("VERBOT");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}else{
			createPlayer(uuid);
			getVerbot(uuid);
		}
		return null;
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static void createPlayer(final String uuid) {
		
			if(!playerExists(uuid)) {
				MySQL.update("INSERT INTO FFAStats(UUID, KILLS, DEATHS, VERBOT, VERBOTUUID, PUNKTE) VALUES ('" + uuid + "', '0', '0', '0', " + null + ", '5');");
		}
		
		
	}
	
	public static Integer getOwnRank(String uuid) {
		int count = 0;
		if(!playerExists(uuid)) {
		ResultSet rs = MySQL.query("SELECT * FROM FFAStats ORDER BY KILLS DESC");
		
		try{
		while(rs.next()) {
			count++;
			String nameduuid = rs.getString("UUID");
			UUID uuid1 = UUID.fromString(nameduuid);
			if(uuid1.toString().equals(uuid)) {
				return count;
			}
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}else{
			createPlayer(uuid);
			getOwnRank(uuid);
		}
		return count;
	}
	
	public static Integer getKills(String uuid) {
		Integer i = 0;
		try{
			if(playerExists(uuid)) {
			ResultSet rs = MySQL.query("SELECT * FROM FFAStats WHERE UUID= '" + uuid + "'");
			if(!rs.next() || Integer.valueOf(rs.getInt("KILLS"))== null) {
				
			}
			i = rs.getInt("KILLS");
			}else{
				createPlayer(uuid);
				getKills(uuid);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static void setKills(String uuid, Integer kills) {
		if(playerExists(uuid)) {
			MySQL.update("UPDATE FFAStats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
		}else{
			createPlayer(uuid);
			setKills(uuid, kills);
		}
	}
	
	public static void addKills(String uuid, Integer kills) {
		if(playerExists(uuid)) {
			setKills(uuid, Integer.valueOf(getKills(uuid)).intValue() + kills.intValue());
		}else{
			createPlayer(uuid);
			addKills(uuid, kills);
		}
	}
	
	public static Integer getDeaths(String uuid) {
		Integer i = 0;
		try{
			if(playerExists(uuid)) {
			ResultSet rs = MySQL.query("SELECT * FROM FFAStats WHERE UUID= '" + uuid + "'");
			if(!rs.next() || Integer.valueOf(rs.getInt("DEATHS"))== null) {
				
			}
			i = rs.getInt("DEATHS");
			}else{
				createPlayer(uuid);
				getDeaths(uuid);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static void setDeaths(String uuid, Integer deaths) {
		if(playerExists(uuid)) {
			MySQL.update("UPDATE FFAStats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
		}else{
			createPlayer(uuid);
			setDeaths(uuid, deaths);
		}
	}
	
	public static void addDeaths(String uuid, Integer deaths) {
		if(playerExists(uuid)) {
			setDeaths(uuid, Integer.valueOf(getDeaths(uuid)).intValue() + deaths.intValue());
		}else{
			createPlayer(uuid);
			addDeaths(uuid, deaths);
		}
	}
	
	public static void setPunkte(String uuid, Integer punkte) {
		if(playerExists(uuid)) {
			MySQL.update("UPDATE FFAStats SET PUNKTE= '" + punkte + "' WHERE UUID= '" + uuid + "';");
		}else{
			createPlayer(uuid);
			setKills(uuid, punkte);
		}
	}
	
	public static void addPunkte(String uuid, Integer punkte) {
		if(playerExists(uuid)) {
			setPunkte(uuid, Integer.valueOf(getPunkte(uuid)).intValue() + punkte.intValue());
		}else{
			createPlayer(uuid);
			addPunkte(uuid, punkte);
		}
	}
	
	public static void removePunkte(String uuid, Integer punkte) {
		if(playerExists(uuid)) {
			setPunkte(uuid, Integer.valueOf(getPunkte(uuid)).intValue() - punkte.intValue());
		}else{
			createPlayer(uuid);
			removePunkte(uuid, punkte);
		}
	}
	
	public static Integer getPunkte(String uuid) {
		Integer i = 0;
		try{
			if(playerExists(uuid)) {
			ResultSet rs = MySQL.query("SELECT * FROM FFAStats WHERE UUID= '" + uuid + "'");
			if(!rs.next() || Integer.valueOf(rs.getInt("PUNKTE"))== null) {
				
			}
			i = rs.getInt("PUNKTE");
			}else{
				createPlayer(uuid);
				getPunkte(uuid);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	
	
	
	
	
	

}
