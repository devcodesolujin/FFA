package me.devcode.FFAKits.Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import me.devcode.FFAKits.FFA;

public class MapReset {
	
	public static HashMap<Integer, String> reset = new HashMap<Integer, String>();
	public static HashMap<Integer, String> resetworld = new HashMap<Integer, String>();
	public static int current = 0;
	public static int currentblock = 0;
	
	public static HashMap<Integer, String> reset2 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> resetworld2 = new HashMap<Integer, String>();
	public static int current2 = 0;
	public static int currentblock2 = 0;
	
	
	@SuppressWarnings("deprecation")
	public static void MapReset(Block b) {
		resetworld.put(current, b.getWorld().getName());
		reset.put(current, b.getLocation().getBlockX() + ";" + b.getLocation().getBlockY() + ";" + b.getLocation().getBlockZ() + ";" + b.getTypeId() + ";" + b.getData());
	current++;
	Bukkit.getScheduler().runTaskLater(FFA.plugin, new BukkitRunnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String array[] = reset.get(currentblock).split(";");
			String world = resetworld.get(currentblock);
			int x = Integer.valueOf(array[0]);
			int y = Integer.valueOf(array[1]);
			int z = Integer.valueOf(array[2]);
			int id = Integer.valueOf(array[3]);
			byte data = Byte.valueOf(array[4]);
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			loc.getBlock().setTypeId(0);
			loc.getBlock().setData((byte) 0);
			currentblock++;
			
		}
	}, 100L);
	}
	
	

}
