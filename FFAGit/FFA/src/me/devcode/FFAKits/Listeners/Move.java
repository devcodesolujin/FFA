package me.devcode.FFAKits.Listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Commands.Vanish;
import me.devcode.FFAKits.Particel.ParticelApi;
import net.minecraft.server.v1_8_R1.EnumParticle;

public class Move implements Listener {
	
	ArrayList<String> ps = new ArrayList<String>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(p.getLocation().getBlock().getType() == Material.IRON_PLATE) {
			if(p.getLocation().subtract(0D, 1D, 0D).getBlock().getType() == Material.REDSTONE_BLOCK) {
				Vector v = p.getLocation().getDirection().multiply(3D).setY(1.5D);
				p.setVelocity(v);
				p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 0.2F, 0.2F);
			}
		}
		if(!Vanish.vanish.contains(p.getName())) {
		
		if(e.getPlayer().hasPermission("mlg.premium")) {
			if(!ps.contains(e.getPlayer().getName())) {
			ps.add(e.getPlayer().getName());
			Location loc = e.getPlayer().getLocation().add(0D, 2D, 0D);
			
			if(p.hasPermission("mlg.premium")) {
				if(FFA.plugin.getConfig().getBoolean("Particel." + "Herzen" + p.getUniqueId().toString()) == true) {
					Random ran = new Random();
					int ranint = ran.nextInt(3);
					int rani = ranint+=1;
					ParticelApi particle = new ParticelApi(EnumParticle.HEART, false, loc, 0.2F, 0, 0.2F, 1, rani);
					particle.sendAll();
				}else if(FFA.plugin.getConfig().getBoolean("Particel." + "Redstone" + p.getUniqueId().toString()) == true) {
					Random ran = new Random();
					int ranint = ran.nextInt(5);
					int rani = ranint+=3;
					ParticelApi particle = new ParticelApi(EnumParticle.REDSTONE, false, loc, 0.2F, 0, 0.2F, 0, rani);
					particle.sendAll();
				}else if(FFA.plugin.getConfig().getBoolean("Particel." + "Snow" + p.getUniqueId().toString()) == true) {
					Random ran = new Random();
					int ranint = ran.nextInt(5);
					int rani = ranint+=3;
					ParticelApi particle = new ParticelApi(EnumParticle.SNOWBALL, false, loc, 0.2F, 0, 0.2F, 0, rani);
					particle.sendAll();
				}else if(FFA.plugin.getConfig().getBoolean("Particel." + "Fire" + p.getUniqueId().toString()) == true) {
					Random ran = new Random();
					int ranint = ran.nextInt(3);
					int rani = ranint+=2;
					ParticelApi particle = new ParticelApi(EnumParticle.LAVA, false, loc, 0.2F, 0, 0.2F, 0, rani);
					particle.sendAll();
				}else if(FFA.plugin.getConfig().getBoolean("Particel." + "Water" + p.getUniqueId().toString()) == true) {
					Random ran = new Random();
					int ranint = ran.nextInt(5);
					int rani = ranint+=3;
					ParticelApi particle = new ParticelApi(EnumParticle.WATER_DROP, false, loc, 0.2F, 0, 0.2F, 0, rani);
					particle.sendAll();
				}
				
			
			
			addCd(e.getPlayer());
			}
			}
			}
		}
	}
	
	
	
	public void addCd(final Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(FFA.plugin, new BukkitRunnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ps.remove(p.getName());
				
			}
		}, 40L);
		
	}

}
