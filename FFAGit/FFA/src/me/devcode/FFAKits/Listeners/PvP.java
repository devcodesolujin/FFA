package me.devcode.FFAKits.Listeners;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.Region.Region;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PlayerConnection;


public class PvP implements Listener {
	
	public static HashMap<String, Integer> scheduler = new HashMap<String, Integer>();
	public static HashMap<String, Scoreboard> board = new HashMap<String, Scoreboard>();
	
	
	@EventHandler
	public void onDamage(EntityDamageByBlockEvent e) {
	e.setCancelled(true);


	}


	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		Entity p = e.getEntity();
		if(p instanceof Player) {
			
		if(Region.isIn(p.getLocation())) {
		e.setCancelled(true);
		}else{
		if(e.getCause() == DamageCause.FALL) {
	e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
		}
		}
		}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		
		Entity p =  e.getEntity();
		if(p instanceof Player && p != null) {
		Entity damager =  e.getDamager();
		
		if(damager instanceof Player && damager != null) {
		if(((Player)damager).getItemInHand() == null) {
			e.setDamage(0.5D);
		}
		Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
		Location loc1 = new Location(damager.getWorld(), damager.getLocation().getX(), damager.getLocation().getY(), damager.getLocation().getZ());
		
		if(Region.isIn(loc1)) {
			damager.sendMessage(FFA.ffam.prefix + "§cDu bist in der §eSave-Zone");
			e.setCancelled(true);
		}else{
			
			JoinQuitListeners.combat.put(p.getName(), damager.getName());
			JoinQuitListeners.combat.put(damager.getName(), p.getName());
			//Schedule mit System.current.. nächstes mal. bb.
			long combat = 15 *1000;
			long current = System.currentTimeMillis();
			long insgesamt = current + combat;
			setKampf((Player) damager);
			setKampf((Player) p);
			Player player = ((Player)p);
			Player player2 = ((Player)damager);
			
			
			
			
			JoinQuitListeners.combattime.put(p.getName(), insgesamt);
			JoinQuitListeners.combattime.put(damager.getName(), insgesamt);
		}
		}
	}
	}
	
	
	
	
	
	@EventHandler
	public void onLeave(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public void setKampf(final Player p) {
		if(scheduler.containsKey(p.getName())) {
			Bukkit.getScheduler().cancelTask(scheduler.get(p.getName()));
			scheduler.remove(p.getName());
		}
		int schedulerint = 0;
		
		scheduler.put(p.getName(), Bukkit.getScheduler().scheduleSyncRepeatingTask(FFA.plugin, new BukkitRunnable() {
			double timer = 16;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
	
				if(timer <= timer) {
					timer-=0.1D;
					//registerScoreboard(p, timer);
					
					DecimalFormat f = new DecimalFormat("#0.00");
					double toFormat = ((double)Math.round(timer*100))/100;
					String format = String.valueOf(toFormat);
					sendTitle(p, "§cKampf endet in §e" + format + " §cSekunden!");
					
					}
					if(timer <= 0D) {
						
						sendTitle(p, null);
						Bukkit.getScheduler().cancelTask(scheduler.get(p.getName()));
						scheduler.remove(p.getName());
					}
			}
		}, 0, 2));
			
			
				
				
				// TODO Auto-generated method stub
			
			
			
		
		
	}
	
	
	public static void sendTitle(Player p, String s) {
		if(s == null) s = "";
		s = ChatColor.translateAlternateColorCodes('&', s);
		s = s.replaceAll("%PLAYER%", p.getDisplayName());
		PlayerConnection packet = ((CraftPlayer)p).getHandle().playerConnection;
		
		IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"" + s + "\"}");
		PacketPlayOutChat packet2 = new PacketPlayOutChat(chat, (byte) 2);
		packet.sendPacket(packet2);
	}
	
	/*public void registerScoreboard(Player p, double timer) {
		if(board.containsKey(p.getName())) {
			if(board.get(p.getName()) == null) {
				
				board.put(p.getName(), Bukkit.getScoreboardManager().getNewScoreboard());
				JoinQuitListeners.setTag(p);
			}
			Scoreboard s = board.get(p.getName());
			
			
				if(s.getObjective("aaa") != null) {
					s.getObjective("aaa").unregister();
					registerScoreboard(p, timer);
				}else{
			Objective object = s.registerNewObjective("aaa", "bbb");
				
				object.setDisplayName("§6FFA §8▏ §cKampflog");
				 object.setDisplaySlot(DisplaySlot.SIDEBAR);
				 Score score1 = object.getScore(Bukkit.getOfflinePlayer("§3Im Kampf: §aJa"));
				 score1.setScore(1);
				 DecimalFormat f = new DecimalFormat("#0.00");
					double toFormat = ((double)Math.round(timer*100))/100;
					Score score2 = object.getScore(Bukkit.getOfflinePlayer("§3Kampfende: §e" + toFormat));
				 score2.setScore(2);
					p.setScoreboard(s);
					
					
				}
		}else{
			board.put(p.getName(), Bukkit.getScoreboardManager().getNewScoreboard());
			registerScoreboard(p, timer);
		}
	}
	*/
	
}
