package me.devcode.FFAKits.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.devcode.FFAKits.FFA;

public class SoundSelector implements Listener {
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§6Gadgets")) {
			if(e.getCurrentItem().getType() == Material.NOTE_BLOCK) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, "§7Wähle einen Sound aus:");
				
				ItemStack istack = new ItemStack(Material.NOTE_BLOCK);
				ItemMeta imeta = istack.getItemMeta();
				imeta.setDisplayName("§6Pferde§8-§6Tod");
				istack.setItemMeta(imeta);
				
				ItemStack istack2 = new ItemStack(Material.NOTE_BLOCK);
				ItemMeta imeta2 = istack2.getItemMeta();
				imeta2.setDisplayName("§6Level§8-§6Up");
				istack2.setItemMeta(imeta2);
				
				ItemStack istack3 = new ItemStack(Material.NOTE_BLOCK);
				ItemMeta imeta3 = istack3.getItemMeta();
				imeta3.setDisplayName("§6Click");
				istack3.setItemMeta(imeta3);
				
				ItemStack istack4 = new ItemStack(Material.NOTE_BLOCK);
				ItemMeta imeta4 = istack4.getItemMeta();
				imeta4.setDisplayName("§6Note§8-§6Pling");
				istack4.setItemMeta(imeta4);
				
				ItemStack istack5 = new ItemStack(Material.BARRIER);
				ItemMeta imeta5 = istack5.getItemMeta();
				imeta5.setDisplayName("§cEntfernen");
				istack5.setItemMeta(imeta5);
				
				
				
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
				ItemMeta glassm =  glass.getItemMeta();
				glassm.setDisplayName("§b???");
				glass.setItemMeta(glassm);
				
				inv.setItem(0, istack);
				inv.setItem(1, istack2);
				inv.setItem(2, istack3);
				inv.setItem(3, istack4);
				inv.setItem(4, glass);
				inv.setItem(5, glass);
				inv.setItem(6, glass);
				inv.setItem(7, glass);
				inv.setItem(8, istack5);
				e.getWhoClicked().openInventory(inv);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick1(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§7Wähle einen Sound aus:")) {
			boolean b = false;
			String name = "";
			Player p = (Player) e.getWhoClicked();
			if(e.getCurrentItem().getType() == Material.AIR) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§b???")){
			e.setCancelled(true);
			p.sendMessage(FFA.ffam.prefix + "§cBald folgen weitere Sounds!");
			}else{
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Pferde§8-§6Tod")) {
				e.setCancelled(true);
				b = true;
				name = "Pferde-Tod";
				p.playSound(p.getLocation(), Sound.HORSE_DEATH, 1, 1);
				p.sendMessage(FFA.ffam.prefix +"§3Du hörst jetzt bei einem Kill den Sound: §6Pferde§8-§6Tod");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Level§8-§6Up")) {
				e.setCancelled(true);
				b = true;
				name = "Level-Up";
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				p.sendMessage(FFA.ffam.prefix +"§3Du hörst jetzt bei einem Kill den Sound: §6Level§8-§6Up");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Click")) {
				e.setCancelled(true);
				b= true;
				name = "Click";
				p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
				
				p.sendMessage(FFA.ffam.prefix +"§3Du hörst jetzt bei einem Kill den Sound: §6Click");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Note§8-§6Pling")) {
				e.setCancelled(true);
				b= true;
				name = "Note-Pling";
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
				
				p.sendMessage(FFA.ffam.prefix +"§3Du hörst jetzt bei einem Kill den Sound: §6Note§8-§6Pling");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§bRemove Sound")) {
				e.setCancelled(true);
				name = null;
				b = false;
				p.sendMessage(FFA.ffam.prefix + "§cDu hörst nun keine §eSounds §cmehr, nach einem Kill!");
			}
			FFA.plugin.getConfig().set("Sound." + "Click" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Sound." + "Pferde-Tod" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Sound." + "Level-Up" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Sound." + "Note-Pling" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Sound." + name +  p.getUniqueId().toString(), b);
			FFA.plugin.saveConfig();
			}
			}
		}

}
