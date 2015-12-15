package me.devcode.FFAKits.Region;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import me.devcode.FFAKits.Cuboid;

public class Region {
	
	public static ArrayList<Cuboid> regions = new ArrayList<Cuboid>();
	
	public static boolean isIn(Location loc) {
		boolean isin = false;
		for(Cuboid cb : regions) {
			if(cb.containsLocation(loc)) {
				isin = true;				
			}else{
				isin = false;
			}
		}
		//Boolean (is in the Cuboid Region)
		return isin;
	}
	
	public static void  addRegion(Location loc, Location loc2) {
		Cuboid cb = new Cuboid(loc, loc2);
		regions.add(cb);
		
		
		//Add Cuboid Region
	}
	
	 

}
