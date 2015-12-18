package me.devcode.FFAKits.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Commands.FFA_Commands;

public class SaveZoneInteract implements Listener {
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
			Player p = e.getPlayer();
			
				if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
				
				if(FFA_Commands.tool.contains(p)) {
					e.setCancelled(true);
					FFA_Commands.loc1.put(p, e.getClickedBlock().getLocation());
					p.sendMessage(FFA.ffam.prefix + "§3Du hast die §e1 Postion §3festgelegt.");
				}
			}else{
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(FFA_Commands.tool.contains(p)) {
						e.setCancelled(true);
						FFA_Commands.loc2.put(p, e.getClickedBlock().getLocation());
						p.sendMessage(FFA.ffam.prefix + "§3Du hast die §e2 Postion §3festgelegt.");
					}
				}
			
		}
	}

}
