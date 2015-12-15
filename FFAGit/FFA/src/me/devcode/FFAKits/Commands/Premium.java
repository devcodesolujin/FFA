package me.devcode.FFAKits.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.devcode.FFAKits.FFA;

public class Premium implements CommandExecutor{
	
	
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.prefix);
			return true;
		}
		Player p = (Player) s;
		if(!p.hasPermission("mlg.premium")) {
			p.sendMessage(FFA.ffam.prefix + "§cDafür hast du keine Berechtigung. Du brauchst den §ePremium §cRang dafür.");
		return true;
		}
		Inventory inv = Bukkit.createInventory(null, 9, "§6Gadgets");
		ItemStack skin = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skinmeta = (SkullMeta) skin.getItemMeta();
		List<String> skinlist = new ArrayList<String>();
		skinlist.add("§3Hier kannst du deinen §eSkin §7auswählen.");
		skinmeta.setDisplayName("§6Skins");
		skinmeta.setLore(skinlist);
		skin.setItemMeta(skinmeta);
		
		ItemStack sound = new ItemStack(Material.NOTE_BLOCK);
		ItemMeta soundmeta = sound.getItemMeta();
		List<String> soundlist = new ArrayList<String>();
		soundlist.add("§7Hier kannst du den Sound bei einem §eKill §7auswählen.");
		soundmeta.setDisplayName("§bSounds");
		soundmeta.setLore(soundlist);
		sound.setItemMeta(soundmeta);
		
		ItemStack particel = new ItemStack(Material.REDSTONE);
		ItemMeta particelmeta = particel.getItemMeta();
		List<String> particellist = new ArrayList<String>();
		particellist.add("§7Hier kannst du die §ePartikel §7auswählen.");
		particelmeta.setDisplayName("§cPartikel");
		particelmeta.setLore(particellist);
		particel.setItemMeta(particelmeta);
		
		inv.setItem(0, skin);
		inv.setItem(4, particel);
		inv.setItem(8, sound);
		p.openInventory(inv);
		
		return true;
	}

}
