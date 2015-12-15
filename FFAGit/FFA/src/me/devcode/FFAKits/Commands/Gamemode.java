package me.devcode.FFAKits.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devcode.FFAKits.FFA;

public class Gamemode implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
		Player p = (Player) s;
		if(!p.hasPermission("mlg.moderator")) {
			p.sendMessage(FFA.ffam.perm);
			return true;
		}
		if(args.length < 1) {
			p.sendMessage(FFA.ffam.prefix +  "§cFehler! /gamemode <1|2|3> (<Spieler>)");
			return true;
		}
		if(args.length == 1) {
			try{
				int gamemode = Integer.parseInt(args[0]);
			}catch(NumberFormatException e) {
				p.sendMessage(FFA.ffam.prefix + "§3Bitte gib eine Zahl ein!");
			}
			int gamemode = Integer.parseInt(args[0]);
			if(gamemode == 0) {
				p.setGameMode(GameMode.SURVIVAL);
			}else if(gamemode == 1) {
				p.setGameMode(GameMode.CREATIVE);
			}else if(gamemode == 2) {
				p.setGameMode(GameMode.ADVENTURE);
			}
			if(gamemode <= 3) {
			p.sendMessage(FFA.ffam.prefix + "§3Du bist nun im §eGamemode " + gamemode);
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cFehler! /gamemode <1|2|3> (<Spieler>)");
			}
			return true;
		}
		if(args.length >= 2) {
		if(Bukkit.getPlayer(args[1]) != null) {
			Player player = Bukkit.getPlayer(args[1]);
			try{
				int gamemode = Integer.parseInt(args[0]);
			}catch(NumberFormatException e) {
				p.sendMessage(FFA.ffam.prefix + "§3Bitte gib eine Zahl ein!");
			}
			int gamemode = Integer.parseInt(args[0]);
			if(gamemode == 0) {
				player.setGameMode(GameMode.SURVIVAL);
			}else if(gamemode == 1) {
				player.setGameMode(GameMode.CREATIVE);
			}else if(gamemode == 2) {
				player.setGameMode(GameMode.ADVENTURE);
			}
			if(gamemode <= 3) {
				Bukkit.getPlayer(args[1]).sendMessage(FFA.ffam.prefix + "§3Du bist nun im §eGamemode " + args[0] + "§3!");
				p.sendMessage(FFA.ffam.prefix + "§3Der Spieler §e" + args[1] + " §3ist nun im §eGamemode " + args[0]);
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cFehler! /gamemode <1|2|3> (<Spieler>)");
			}
			
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDer Spieler ist nicht online!");
			}
		}
		return true;
	}

}
