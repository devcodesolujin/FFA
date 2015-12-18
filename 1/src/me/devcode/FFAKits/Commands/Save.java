package me.devcode.FFAKits.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Listeners.KitListeners;
import me.devcode.FFAKits.Region.Region;

public class Save implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(!(s instanceof Player)) {
			s.sendMessage(FFA.ffam.noplayer);
			return true;
		}
	
	
		Player p = (Player) s;
		if(Region.isIn(p.getLocation())) {
			
			KitListeners.save.add(p.getName());
			
			KitListeners.itemstack.put(p.getName(), p.getInventory().getContents());
			p.sendMessage(FFA.ffam.prefix + "§eInventar gespeichert!");
			
			if(p.getInventory().getArmorContents()  != null) {
			KitListeners.itemstack2.put(p.getName(), p.getInventory().getArmorContents());
			}
		}else{
			p.sendMessage(FFA.ffam.prefix + "§cDu bist nicht in der §eSave-Zone§c!");
		}
		return true;
	
	}

}
