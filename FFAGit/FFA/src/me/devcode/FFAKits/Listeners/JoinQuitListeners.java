package me.devcode.FFAKits.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.MySQLStats;

public class JoinQuitListeners implements Listener {
	
	public static HashMap<String, String> combat = new HashMap<String, String>();
	public static HashMap<String, Long> combattime = new HashMap<String, Long>();
	  String ADMIN = "§4Administrator §8▏§4 ";
	  String DEVELOPER = "§bDeveloper §8▏§b ";
	  String BAUTEAM = "§2Bauteam §8▏§2 ";
	  String YOUTUBER = "§5";
	  String PREMIUM = "§6";
	  String SPIELER = "§9";
	  String MODERATOR = "§cModerator §8▏§c ";
	  
	  @EventHandler
	  public void onChat1(AsyncPlayerChatEvent e)
	  {
	    Player p = e.getPlayer();
	    String msg = e.getMessage();
	   
	    if (p.hasPermission("mlg.admin.nametag")) {
	      e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " +this.ADMIN + p.getName() + "§8 » §7" + msg);
	    } else if (p.hasPermission("mlg.developer.nametag")) {
	      e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " + this.DEVELOPER + p.getName() + "§8 » §7" + msg);
	    } else if (p.hasPermission("mlg.bauteam.nametag")) {
	      e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " +this.BAUTEAM + p.getName() + "§8 » §7" + msg);
	    } else if (p.hasPermission("mlg.youtuber.nametag")) {
	      e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " +this.YOUTUBER + p.getName() + "§8 » §7" + msg);
	    } else if (p.hasPermission("mlg.premium.nametag")) {
	      e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " + this.PREMIUM + p.getName() + "§8 » §7" + msg);
	    } else if (p.hasPermission("mlg.spieler.nametag")) {
	      e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " +this.SPIELER + p.getName() + "§8 » §7" + msg);
	    } else if(p.hasPermission("mlg.moderator.nametag")) {
	    	e.setFormat("§9" + MySQLStats.getPunkte(p.getUniqueId().toString()) + " §8» " +this.MODERATOR + p.getName() + "§8 » §7" + msg);
	    }
	    }
	  
	  @EventHandler
	  public void onChat(AsyncPlayerChatEvent e) {
		  Player p = e.getPlayer();
		    String msg = e.getMessage();
	  
		
		    for(String words : FFA.plugin.getConfig().getStringList("Blacklist")) {
		    if(msg.contains(words)) {
		    	if(!p.hasPermission("mlg.blacklist")) {
		    	e.setCancelled(true);
		    	
		    	p.sendMessage(FFA.ffam.prefix + "§cBitte achte auf deine Wortwahl!");
		    	if(msg.contains("Hacker") || msg.contains("Hax") || msg.contains("Hacks")) {
		    		p.sendMessage(FFA.ffam.prefix + "§cReporte Hacker mit §e/report");
		    	}
		    	Player player = null;
		    	for(Player all : Bukkit.getOnlinePlayers()) {
		    		if(all.hasPermission("mlg.blacklist")) {
		    			player = all;
		    		}
		    	}
		    			player.sendMessage("§8▎ §6Blacklist §8» §c" + p.getName() + ": §7" + msg);
		    		
		    	}
		    	
		    	
		    }
		    }
	  
	  }
	  
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		
		 
		
		MySQLStats.createPlayer(e.getPlayer().getUniqueId().toString());
		e.getPlayer().sendMessage("");
		    e.getPlayer().sendMessage("");
		    e.getPlayer().sendMessage("§8● §cTeams sind §nverboten §cund werden mit einem §eBan§c bestraft.");
		    e.getPlayer().sendMessage("");
		    e.getPlayer().sendMessage("");
		e.getPlayer().setPlayerListName(e.getPlayer().getName());
		
		
		if(FFA.plugin.getConfig().getBoolean("Skin." + ".Boolean" + e.getPlayer().getUniqueId().toString()) == true) {
			String value = FFA.plugin.getConfig().getString("Skin." + ".Value" +e.getPlayer().getUniqueId().toString());
			String signature = FFA.plugin.getConfig().getString("Skin." + ".Signature" + e.getPlayer().getUniqueId().toString());
			GameProfile gp = ((CraftPlayer)e.getPlayer()).getProfile();
			gp.getProperties().clear();
			gp.getProperties().put("textures", new Property("textures", value, signature));
			
			
			Bukkit.getScheduler().runTaskLater(FFA.plugin, new BukkitRunnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.hidePlayer(e.getPlayer());
					}
				}
			}, 1);
			
			Bukkit.getScheduler().runTaskLater(FFA.plugin, new BukkitRunnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.showPlayer(e.getPlayer());
					}
				}
			}, 30);
		}
		
		
		
		e.setJoinMessage(null);
		e.getPlayer().getInventory().setArmorContents(null);
		e.getPlayer().getInventory().clear();
		
		Damageable player = (Damageable) e.getPlayer();
		if(player.getHealth() > 0D) {
		e.getPlayer().setHealth(20D);
		}else{
			e.getPlayer().spigot().respawn();
		}
		e.getPlayer().setFoodLevel(20);
		e.getPlayer().setFlying(false);
		e.getPlayer().setAllowFlight(false);
		e.getPlayer().setFireTicks(0);
		e.getPlayer().setLevel(0);
		e.getPlayer().setExp((float) 0);
		e.getPlayer().getInventory().clear();
		ItemStack itemstack = new ItemStack(Material.CHEST);
		ItemMeta itemmeta = itemstack.getItemMeta();
		itemmeta.setDisplayName("§cKit Selector");
		itemstack.setItemMeta(itemmeta);
		e.getPlayer().getInventory().setItem(0, itemstack);
		if(e.getPlayer().hasPermission("mlg.premium")) {
			
			
			ItemStack is = new ItemStack(Material.NETHER_STAR);
			ItemMeta im = is.getItemMeta();
			List<String> li= new ArrayList<String>();
			li.add("§cHier mit kommst du in das §ePremium §cMenü.");
			li.add("§eDu kannst folgendes einstellen: Particel, Sounds, Skins");
			im.setLore(li);
			im.setDisplayName("§6Gadgets");
			is.setItemMeta(im);
			e.getPlayer().getInventory().setItem(8, is);
			}
		if(FFA.plugin.getConfig().getString("Spawn." + ".World") != null) {
			if(!e.getPlayer().hasPlayedBefore() == false) {
			World w = Bukkit.getWorld(FFA.plugin.getConfig().getString("Spawn." + ".World"));
			double x = FFA.plugin.getConfig().getDouble("Spawn." + ".X");
			double y = FFA.plugin.getConfig().getDouble("Spawn." + ".Y");
			double z = FFA.plugin.getConfig().getDouble("Spawn." + ".Z");
			Location loc = new Location(w, x, y, z);
			e.getPlayer().teleport(loc);
			}else{
				World w = Bukkit.getWorld(FFA.plugin.getConfig().getString("Spawn." + ".World"));
				double x = FFA.plugin.getConfig().getDouble("Spawn." + ".X");
				double y = FFA.plugin.getConfig().getDouble("Spawn." + ".Y");
				double z = FFA.plugin.getConfig().getDouble("Spawn." + ".Z");
				Location loc = new Location(w, x, y, z);
				e.getPlayer().teleport(loc);
			}
		}
		Bukkit.getScheduler().runTaskLater(FFA.plugin, new BukkitRunnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MySQLStats.createPlayer(e.getPlayer().getUniqueId().toString());
			}
		}, 20L);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
	
		if(e.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
			if(p.isOp() || p.hasPermission("mlg.premium")) {
				e.allow();
				
				if((Bukkit.getOnlinePlayers() != null) && (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers())) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						if((pl.isOp()) || (pl.hasPermission("mlg.premium")))
							continue;
						pl.kickPlayer("§8[§6MLG§8]\n"
							    + "§cDu wurdest von einem §6Premium §cgekickt!\n" +
								"\n" +
								"§cKaufe dir den §6Premium Rang §cum zu joinen\n");
						return;
					}
				}
			
		}else{
			e.disallow(Result.KICK_FULL, "§cDer Server ist voll! Du brauchst den §6Premium Rang §cum trotzdem joinen zu können!");
		}
	}
	}
	
	@EventHandler
	public void onW(WeatherChangeEvent e) {
		
		e.setCancelled(true);
	}
	
	@EventHandler 
	public void onQuit(PlayerQuitEvent e) {
		
		if(combat.containsKey(e.getPlayer().getName())) {
			if(combattime.containsKey(e.getPlayer().getName())) {
			long combatzeit = combattime.get(e.getPlayer().getName());
			long current = System.currentTimeMillis();
			long insgesamt = combatzeit - current;
			if(insgesamt > 0) {
				e.getPlayer().setHealth(0D);
				e.setQuitMessage(FFA.ffam.prefix + "§cDer Spieler §e" + e.getPlayer().getName() + " §chat während des Kampfes den Server verlassen.");
			}else{
				e.setQuitMessage(null);
				combattime.remove(e.getPlayer().getName());
				combat.remove(e.getPlayer().getName());
			}
			
			}else{
				e.setQuitMessage(null);
			}
			}else{
			e.setQuitMessage(null);
		}
	}
	
	/*public static void setTag(Player p) {
		
		
		
	
		
		

		
		
				
				
				
				
		p.setPlayerListName("§4Admin §7▏ §4" + p.getName());
		p.setCustomName("§4Admin §77▏  §4" + p.getName());		
		p.setCustomNameVisible(true);		
		
		
		
		
	
	}
	
	public static void setTag2(Player p) {
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				if(p.hasPermission("mlg.spieler")){
					
						
					
					
					p.setPlayerListName("§9" + p.getName());
					p.setCustomName("§9" + p.getName());
					p.setCustomNameVisible(true);
					}
				
				if(p.hasPermission("mlg.premium")) {
				
					
					
					p.setPlayerListName("§6" + p.getName());
					
				}
				if(p.hasPermission("mlg.moderator")) {
				
					
					
					p.setPlayerListName("§cMod §7▏§c " + p.getName());
					
				} if(p.hasPermission("mlg.youtuber")) {
					
					
					
					p.setPlayerListName("§5YT §7▏§5 " + p.getName());
					
				} if(p.hasPermission("mlg.bauteam")) {
					
					
					p.setPlayerListName("§2" + p.getName());
					
				
	
		
	}
	}
	*/
	
	
	
}
