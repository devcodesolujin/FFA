package me.devcode.FFAKits.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Premium2 implements Listener {
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getMaterial().equals(Material.NETHER_STAR)) {
				e.setCancelled(true);
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
				e.getPlayer().openInventory(inv);
				
			}
		}
	}

}
