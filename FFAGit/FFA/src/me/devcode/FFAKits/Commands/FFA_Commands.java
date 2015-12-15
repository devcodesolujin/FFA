package me.devcode.FFAKits.Commands;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Listeners.JoinQuitListeners;
import me.devcode.FFAKits.Listeners.KitListeners;
import me.devcode.FFAKits.Region.Region;

public class FFA_Commands implements CommandExecutor{
	//Tool for the save spawn (simple worldedit)
	public static ArrayList<Player> tool = new ArrayList<Player>();
	public static HashMap<Player, Location> loc1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> loc2 = new HashMap<Player, Location>();
	public static ArrayList<String> build = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		if(args.length == 0) {
			s.sendMessage(FFA.ffam.prefix + "§cZu wenige Argumente...");
			return true;
		}
		if(args[0].equalsIgnoreCase("setsavespawn")) {
			Player p = (Player) s;
			if(tool.contains(p)) {
				if(loc1.containsKey(p) && loc2.containsKey(p)) {
					double x1 = loc1.get(p).getX();
					double x2 = loc2.get(p).getX();
					double y1 = loc1.get(p).getY();
					double y2 = loc2.get(p).getY();
					double z1 = loc1.get(p).getZ();
					double z2 = loc2.get(p).getZ();
					//Hatte loc1 überall
					World w = loc1.get(p).getWorld();
					FFA.plugin.getConfig().set("SaveZone." + ".X", x1);
					FFA.plugin.getConfig().set("SaveZone." + ".Y", y1);
					FFA.plugin.getConfig().set("SaveZone." + ".Z", z1);
					FFA.plugin.getConfig().set("SaveZone." + ".World", w.getName());
					FFA.plugin.getConfig().set("SaveZone." + ".X2", x2);
					FFA.plugin.getConfig().set("SaveZone." + ".Y2", y2);
					FFA.plugin.getConfig().set("SaveZone." + ".Z2", z2);
					FFA.plugin.saveConfig();
					p.sendMessage(FFA.ffam.prefix + "§3Die §eSave-Zone §3wurde gesetzt.");
					tool.remove(p);
				}else{
					p.sendMessage(FFA.ffam.prefix + "§cMindestens einer der Postionen ist empty.");
					return true;
				}
			}else{
				p.sendMessage(FFA.ffam.prefix + "§eBitte setze nun die Punkte §c1 & 2");
				p.sendMessage(FFA.ffam.prefix + "§e1: Links klick. 2: Rechts klick.");
				tool.add(p);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("setspawn")) {
			Player p = (Player) s;
			double x = p.getLocation().getX();
			double y = p.getLocation().getY();
			double z = p.getLocation().getZ();
			World w = p.getWorld();
			FFA.plugin.getConfig().set("Spawn." + ".World", w.getName());
			FFA.plugin.getConfig().set("Spawn." + ".X", x);
			FFA.plugin.getConfig().set("Spawn." + ".Y", y);
			FFA.plugin.getConfig().set("Spawn." + ".Z", z);
			FFA.plugin.saveConfig();
			p.sendMessage(FFA.ffam.prefix + "§eDu hast den §cSpawn §egesetzt.");
			
			return true;
		}
		if(args[0].equalsIgnoreCase("combat")) {
			Player p = (Player) s;
			if(JoinQuitListeners.combat.containsKey(p.getName())) {
			if(JoinQuitListeners.combattime.containsKey(p.getName())) {
				long combatzeit = JoinQuitListeners.combattime.get(p.getName());
				long current = System.currentTimeMillis();
				long insgesamt = combatzeit - current;
				
				if(insgesamt > 0) {
					//Andernfalls wäre die zahl viel zu klein ^.^
					Time time = new Time(combatzeit);
					p.sendMessage(FFA.ffam.prefix + "§eDein Combat hält noch bis: §c" + time.toLocaleString());
				}else{
					p.sendMessage(FFA.ffam.prefix + "§cDu hast keine Combat-Zeit!");
				}
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDu hast keine Combat-Zeit!");
			}
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDu hast keine Combat-Zeit!");
			}
				
		}
		
		if(args[0].equalsIgnoreCase("build")) {
			Player p = (Player) s;
			if(!p.hasPermission("ffa.build")) {
				p.sendMessage(FFA.ffam.perm);
				return true;
			}
			if(build.contains(p.getName())) {
			p.sendMessage(FFA.ffam.prefix + "§eDu kannst nun nicht mehr bauen");
			build.remove(p.getName());
			}else{
				p.sendMessage(FFA.ffam.prefix + "§eDu kannst nun bauen");
				build.add(p.getName());
			}
		}
		
		return true;
	}

}
