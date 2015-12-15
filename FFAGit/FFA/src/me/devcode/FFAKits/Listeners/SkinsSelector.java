package me.devcode.FFAKits.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.devcode.FFAKits.FFA;

public class SkinsSelector implements Listener{
	
	
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§6Gadgets")) {
			if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, "§7Wähle einen Skin aus:");
				
				
				
				
				//Setze deine Skins
				ItemStack ss = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				
				SkullMeta ssmeta = (SkullMeta) ss.getItemMeta();
				
					ssmeta.setOwner(Bukkit.getOfflinePlayer("_Rainbow_").getName());
				ssmeta.setDisplayName("§eRainbow");
				ss.setItemMeta(ssmeta);
				
				
				
				
				
				
				
				ItemStack defaulti = new ItemStack(Material.BARRIER);
				ItemMeta defaultm =  defaulti.getItemMeta();
				List<String> list = new ArrayList<String>();
				list.add("§3Entferne deinen §eSkin");
				defaultm.setDisplayName("§cEntfernen");
				defaultm.setLore(list);
				defaulti.setItemMeta(defaultm);
			
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
				ItemMeta glassm =  glass.getItemMeta();
				glassm.setDisplayName("§b???");
				glass.setItemMeta(glassm);
				
				
				
			
				inv.setItem(0, ss);	
				inv.setItem(1, glass);
				inv.setItem(2, glass);
				inv.setItem(3, glass);
				inv.setItem(4, glass);
				inv.setItem(5, glass);
				inv.setItem(6, glass);
				inv.setItem(7, glass);
				inv.setItem(8, defaulti);
				e.getWhoClicked().openInventory(inv);
				}
			}
		}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick1(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§7Wähle einen Skin aus:")) {
			String value = "";
			String signature = "";
			boolean b = false;
			final Player p = (Player) e.getWhoClicked();
			if(e.getCurrentItem().getType() == Material.AIR) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§b???")){
			e.setCancelled(true);
			p.sendMessage(FFA.ffam.prefix + "§cDu kannst als §5Youtuber §cauf unserem §eTeamspeak §cdeinen §6Skin§c anfordern.");
			}else{
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§eRainbow")) {
				e.setCancelled(true);
				 value = "eyJ0aW1lc3RhbXAiOjE0NDkzNDI2NjQyMjEsInByb2ZpbGVJZCI6ImZmMzBhODgxOGRlYzRiMmZiY2ZlZjljMDUzZDBiNWVkIiwicHJvZmlsZU5hbWUiOiJfUmFpbmJvd18iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkMTcwMmQxOGQxOThmMmIxMzA1ODQ1MzI3OTQyMzA1OGNmOTA3ZjMxNjM4NzZkYzg4YjBlMjJkMTAifX19";
				 signature = "hsHvGA/K1wURgxNb9RDGt+XfLLxQQygVSwhSSTvAFfjh4dpXzB1oSg+lSz1ERjBdmK0PjnTtyk7QSe64F3hyemVCwzHE1SmxiZnEIirS+oI2ViK6WMkKYsAxvmLCXKWYalyliDfAAk0xTlIydjm2GL/tLh8pPGyrM+SKGij6WXJdVNLRFJK4yWXi0ut1XmT3sQWoO9GVONOpCmu8lyX+TyoUYg0/1FG8Zp9jWr9+rGqbEeVO/pOkboEKm3WCAV2tnxZ653n8y5lQPT3xGOUp+RIrtBeCmwjbS8fs4k4Kt6tb2SUE/7mYvU4lxRX/AE3jSv3T1KU8oPvWyfDGXztrim+oSLgrwFFAHJK5Cq6UphMJblO5N7KYmPymPIjbnGhxOivuuaxRwYMBXKh+yxPRoTioNHgenHliIsmCDdTNshzVBuqaiH0p3cw6x1mKhZ6uaEqMGx7B2xFl6ftiX1L/jhHThUR4ZYDdmdm2mRey9H89yTV85Ek9wwSpHHFHzYkyyorAoTZL0qiip/q3Wswp1D4ZA56OvH+5kpNdvFIFOBJB6zK+vtaTiWgHZaK4MCawJf208yHJ2kxb0+aUbnWAvHb0nLwdOdHhWcNhdQSI0fMeRgufJNuTrnW067gcO4iabfmvUxSvZIVRS32IdxhhlGkbhJzbgf7OmKjL0mDpcxg=";
				b = true;
				
				
				p.sendMessage(FFA.ffam.prefix + "§3Du musst neu connecten, um den §eRainbow §eSkin §3zu erhalten!");
				
			}
			FFA.plugin.getConfig().set("Skin." + ".Boolean" + p.getUniqueId().toString(), b);
			FFA.plugin.getConfig().set("Skin." + ".Value" + p.getUniqueId().toString(), value);
			FFA.plugin.getConfig().set("Skin." + ".Signature" + p.getUniqueId().toString(), signature);
			FFA.plugin.saveConfig();
			}
		}
		}


}
