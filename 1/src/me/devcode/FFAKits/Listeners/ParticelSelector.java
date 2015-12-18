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

public class ParticelSelector implements Listener {
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§6Gadgets")) {
			if(e.getCurrentItem().getType() == Material.REDSTONE) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, "§7Wähle einen Partikel aus:");
				ItemStack istack = new ItemStack(Material.RED_ROSE);
				ItemMeta imeta = istack.getItemMeta();
				imeta.setDisplayName("§4Herzen");
				istack.setItemMeta(imeta);
				
				ItemStack istack2 = new ItemStack(Material.REDSTONE);
				ItemMeta imeta2 = istack2.getItemMeta();
				imeta2.setDisplayName("§cRedstone");
				istack2.setItemMeta(imeta2);
				
				ItemStack istack3 = new ItemStack(Material.SNOW_BALL);
				ItemMeta imeta3 = istack3.getItemMeta();
				imeta3.setDisplayName("§rSnow");
				istack3.setItemMeta(imeta3);
				
				ItemStack istack4 = new ItemStack(Material.FIREBALL);
				ItemMeta imeta4 = istack4.getItemMeta();
				imeta4.setDisplayName("§6Fire");
				istack4.setItemMeta(imeta4);
				
				ItemStack istack5 = new ItemStack(Material.WATER_BUCKET);
				ItemMeta imeta5 = istack5.getItemMeta();
				imeta5.setDisplayName("§bWater");
				istack5.setItemMeta(imeta5);
				
				ItemStack istack6 = new ItemStack(Material.BARRIER);
				ItemMeta imeta6 = istack6.getItemMeta();
				imeta6.setDisplayName("§cEntfernen");
				istack6.setItemMeta(imeta6);
				
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
				ItemMeta glassm =  glass.getItemMeta();
				glassm.setDisplayName("§b???");
				glass.setItemMeta(glassm);
				
				inv.setItem(0, istack);
				inv.setItem(1, istack2);
				inv.setItem(2, istack3);
				inv.setItem(3, istack4);
				inv.setItem(4, istack5);
				inv.setItem(5, glass);
				inv.setItem(6, glass);
				inv.setItem(7, glass);
				inv.setItem(8, istack6);
				
				e.getWhoClicked().openInventory(inv);
			}
	}

	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick1(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§7Wähle einen Partikel aus:")) {
			boolean b = false;
			String name = "";
			Player p = (Player) e.getWhoClicked();
			
			if(e.getCurrentItem().getType() == Material.AIR) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§b???")){
			e.setCancelled(true);
			p.sendMessage(FFA.ffam.prefix + "§cBald folgen weitere Partikel!");
			}else{
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§4Herzen")) {
				e.setCancelled(true);
				b = true;
				name = "Herzen";
				p.sendMessage(FFA.ffam.prefix +"§3Du siehst jetzt folgende Partikel: §4Herzen");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§cRedstone")) {
				e.setCancelled(true);
				b = true;
				name = "Redstone";
				p.sendMessage(FFA.ffam.prefix +"§3Du siehst jetzt folgende Partikel: §cRedstone");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§rSnow")) {
				e.setCancelled(true);
				b= true;
				name = "Snow";
				
				
				p.sendMessage(FFA.ffam.prefix +"§3Du siehst jetzt folgende Partikel: §rSnow");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Fire")) {
				e.setCancelled(true);
				b= true;
				name = "Fire";
				p.sendMessage(FFA.ffam.prefix +"§3Du siehst jetzt folgende Partikel: §6Fire");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§cEntfernen")) {
				e.setCancelled(true);
				b = false;
				name = null;
				p.sendMessage(FFA.ffam.prefix + "§3Du siehst nun keine Partikels mehr!");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§bWater")) {
				e.setCancelled(true);
				b = true;
				name = "Water";
				p.sendMessage(FFA.ffam.prefix  + "§3Du siehst jetzt folgende Partikel: §bWater");
			}
			FFA.plugin.getConfig().set("Particel." + "Herzen" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Particel." + "Water" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Particel." + "Redstone" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Particel." + "Snow" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Particel." + "Fire" +  p.getUniqueId().toString(), null);
			FFA.plugin.getConfig().set("Particel." + name +  p.getUniqueId().toString(), b);
			FFA.plugin.saveConfig();
			}
			}
	}
}
