package me.devcode.FFAKits.Listeners;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Region.Region;

public class KitListeners implements Listener {
	
	public static HashMap<String, ItemStack[]> itemstack = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> itemstack2 = new HashMap<String, ItemStack[]>();
	public static HashMap<String, String> kitname = new HashMap<String, String>();
	public static List<String> save = new ArrayList<String>();
	
	//Kits 
	@EventHandler
	public void onKitInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		if(e.getMaterial().equals(Material.CHEST)) {
			e.setCancelled(true);
			Inventory inv = Bukkit.createInventory(null, 9, "§eWähle ein Kit aus:");
			ItemStack krieger = new ItemStack(Material.GOLD_INGOT);
			ItemMeta kriegermeta = krieger.getItemMeta();
			kriegermeta.setDisplayName("§6Premium");
			List<String> kriegerlist = new ArrayList<String>();	
			kriegermeta.setLore(kriegerlist);
			krieger.setItemMeta(kriegermeta);
			
			ItemStack schütze = new ItemStack(Material.EMERALD);
			ItemMeta schützemeta = schütze.getItemMeta();
			schützemeta.setDisplayName("§aSpieler");
			List<String> schützenliste = new ArrayList<String>();	
			schützemeta.setLore(schützenliste);
			schütze.setItemMeta(schützemeta);
			inv.setItem(8, krieger);
			inv.setItem(0, schütze);
		e.getPlayer().openInventory(inv);	
		}		
	}
	}
	
	@EventHandler
	public void onInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().contains("§eWähle ein Kit aus:")) {
			if(e.getCurrentItem().getType() == Material.AIR) {
				e.setCancelled(true);
			}else{
			if(e.getCurrentItem().getType() == Material.EMERALD) {
				e.setCancelled(true);
				if(Region.isIn(p.getLocation())) {
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					
					ItemStack i1 = FFA.ItemStackName1(Material.IRON_SWORD, 0, 1, null, null);
					ItemStack i2 = FFA.ItemStackName1(Material.FISHING_ROD, 0, 1, null, null);
					ItemStack i3 = FFA.ItemStackName1(Material.BOW, 0, 1, null, null);
					ItemStack i4 = FFA.ItemStackName1(Material.ARROW, 0, 8, null, null);
					
					ItemStack i5 = FFA.ItemStackName1(Material.FIREBALL, 0, 1, null, null);
					p.getInventory().setBoots(FFA.ItemStackName1(Material.IRON_BOOTS, 0, 1, null, null));
					p.getInventory().setLeggings(FFA.ItemStackName1(Material.IRON_LEGGINGS, 0, 1, null, null));
					p.getInventory().setChestplate(FFA.ItemStackName1(Material.IRON_CHESTPLATE, 0, 1, null, null));
					p.getInventory().setHelmet(FFA.ItemStackName1(Material.IRON_HELMET, 0, 1, null, null));
					e.getView().close();
					p.getInventory().setItem(0, i1);
					p.getInventory().setItem(1, i2);
					p.getInventory().setItem(2, i3);
					p.getInventory().setItem(3, i4);
					p.getInventory().setItem(8, i5);
					p.sendMessage(FFA.ffam.prefix + "§3Du hast nun das §aSpieler §3Kit");
					kitname.put(p.getName(), "Spieler");
					
						p.sendMessage(FFA.ffam.prefix + "§eInventar gespeichert!");
						KitListeners.save.add(p.getName());
						itemstack.put(p.getName(), p.getInventory().getContents());
						 p.getInventory().clear();
						 ItemStack[] istack = KitListeners.itemstack.get(p.getName());
							p.getInventory().setContents(istack);
			}else{
				p.sendMessage(FFA.ffam.prefix + "§cDu bist nicht in der §eSave-Zone§c!");
			}
				
			}else
				if(e.getCurrentItem().getType() == Material.GOLD_INGOT) {
				if(p.hasPermission("ffa.premium")) {
				e.setCancelled(true);
				if(Region.isIn(p.getLocation())) {
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				
				ItemStack i1 = FFA.ItemStackName1(Material.IRON_SWORD, 0, 1, null, null);
				ItemStack i2 = FFA.ItemStackName1(Material.FISHING_ROD, 0, 1, null, null);
				ItemStack i3 = FFA.ItemStackName1(Material.BOW, 0, 1, null, null);
				ItemStack i4 = FFA.ItemStackName1(Material.ARROW, 0, 16, null, null);
				
				ItemStack i5 = FFA.ItemStackName1(Material.FIREBALL, 0, 2, null, null);
				p.getInventory().setBoots(FFA.ItemStackName1(Material.IRON_BOOTS, 0, 1, null, null));
				p.getInventory().setLeggings(FFA.ItemStackName1(Material.IRON_LEGGINGS, 0, 1, null, null));
				p.getInventory().setChestplate(FFA.ItemStackName1(Material.IRON_CHESTPLATE, 0, 1, null, null));
				p.getInventory().setHelmet(FFA.ItemStackName1(Material.IRON_HELMET, 0, 1, null, null));
				e.getView().close();
				p.getInventory().setItem(0, i1);
				p.getInventory().setItem(1, i2);
				p.getInventory().setItem(2, i3);
				p.getInventory().setItem(3, i4);
				p.getInventory().setItem(8, i5);
				p.sendMessage(FFA.ffam.prefix + "§3Du hast nun das §6Premium §3Kit");
				kitname.put(p.getName(), "Premium");
				
					p.sendMessage(FFA.ffam.prefix + "§eInventar gespeichert!");
					KitListeners.save.add(p.getName());
					itemstack.put(p.getName(), p.getInventory().getContents());
					 p.getInventory().clear();
					 ItemStack[] istack = KitListeners.itemstack.get(p.getName());
						p.getInventory().setContents(istack);
			        
					
				}else{
					p.sendMessage(FFA.ffam.prefix + "§cDu bist nicht in der §eSave-Zone§c!");
				}
				
				//DeathListener.updateScoreboard(p);
				}else{
					e.setCancelled(true);
					p.sendMessage(FFA.ffam.prefix + "§cFür dieses Kit brauchst du den §6Premium §cRang!");
				}
				}
			}
		}
	}
	
	public ItemStack getItemStack(Material m) {
		
		return new ItemStack(m);
	}

}
