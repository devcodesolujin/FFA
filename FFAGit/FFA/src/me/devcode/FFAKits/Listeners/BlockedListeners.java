package me.devcode.FFAKits.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Commands.FFA_Commands;
import me.devcode.FFAKits.Region.Region;


public class BlockedListeners implements Listener{
	
	
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(Region.isIn(e.getPlayer().getLocation())){
			e.setCancelled(true);	
			}else{
		if(e.getBlock().getType() == Material.FIRE || e.getBlock().getType() == Material.WEB) {
			e.setCancelled(false);
			
		
		}else{
			if(FFA_Commands.build.contains(e.getPlayer().getName())) {
				
				e.setCancelled(false);
			}else{
		e.setCancelled(true);
			}
		}
			}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPick(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(Region.isIn(e.getPlayer().getLocation())){
		e.setCancelled(true);	
		}else{
			if(e.getBlock().getType() == Material.FIRE || e.getBlock().getType() == Material.WEB || e.getBlock().getType() == Material.LAVA || e.getBlock().getType() == Material.WATER) {
		
			e.setCancelled(false);
			MapReset.MapReset(e.getBlock());
		}else{
			if(FFA_Commands.build.contains(e.getPlayer().getName())) {
				
				e.setCancelled(false);
			}else{
		e.setCancelled(true);
			}
			
		}
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	
	public void onInteract(PlayerInteractEvent e) {
		if(e.getClickedBlock().getType() == Material.FENCE) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(FFA.ffam + "§cUm einen §eMinecraft §cBug zu verhindern darfst du keinen Zaun rechtsklicken oder linksklicken.");
		}
	}

}
