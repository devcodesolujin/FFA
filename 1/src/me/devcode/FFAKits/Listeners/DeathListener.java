package me.devcode.FFAKits.Listeners;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.devcode.FFAKits.FFA;
import me.devcode.FFAKits.MySQLStats;

public class DeathListener implements Listener {
	
	public static HashMap<String, Integer> kills = new HashMap<String, Integer>();
	public static HashMap<String, Integer> deaths = new HashMap<String, Integer>();
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		
		Player p = (Player) e.getEntity();
		PvP.sendTitle(p, null);
		p.setLevel(0);
		
		e.getDrops().clear();
		MySQLStats.addDeaths(p.getUniqueId().toString(), 1);
		
		//updateScoreboard(p);
	
		Player killer = (Player) e.getEntity().getKiller();
		
		if(JoinQuitListeners.combat.containsKey(p.getName())) {
			if(killer == null) {
			
			p.sendMessage(FFA.ffam.prefix + "§cDu bist gestorben, weil du dich während eines §eKampfes §causgelogt hast.");
			Player killa = (Player) Bukkit.getOfflinePlayer(JoinQuitListeners.combat.get(p.getName()));
			if(killa.getLevel() == 4) {
				killa.sendMessage(FFA.ffam.prefix + "§3Du hast einen §6Killstreak von §e5 §3und bekommst §e1 §6Cobweb!");
				killa.getInventory().addItem(new ItemStack(Material.WEB));
			}
			playSound(killa);
			killa.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
			killa.sendMessage(FFA.ffam.prefix +"§3Du hast den Spieler §e" + p.getDisplayName() + " §3getötet");
			MySQLStats.addKills(killa.getUniqueId().toString(), 1);
			if(MySQLStats.getPunkte(p.getUniqueId().toString())/40 < 1) {
				MySQLStats.addPunkte(killa.getUniqueId().toString(), 1);
				MySQLStats.removePunkte(p.getName(), 1);
				killa.sendMessage(FFA.ffam.prefix + "§3Der Spieler §e" + p.getName() + " §3hat §e1 Punkt gedroppt.");
				p.sendMessage(FFA.ffam.prefix + "§3Du hast §6" + 1 + " §3Punkt gedroppt!");
			}else{
				MySQLStats.addPunkte(killa.getUniqueId().toString(), Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40));
				MySQLStats.removePunkte(p.getName(), Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40));
				p.sendMessage(FFA.ffam.prefix + "§3Du hast §6" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40) + " §3Punkte gedroppt!");
				killa.sendMessage(FFA.ffam.prefix + "§3Der Spieler §6" + p.getName() + " §3hat §6" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40) + " Punkte gedroppt.");
			}
			killa.setLevel(killa.getLevel()+1);
			//updateScoreboard(killa);	
			}else{
				if(MySQLStats.getPunkte(p.getUniqueId().toString())/40 < 1) {
					MySQLStats.addPunkte(killer.getUniqueId().toString(), 1);
					MySQLStats.removePunkte(p.getUniqueId().toString(), 1);
					killer.sendMessage(FFA.ffam.prefix + "§3Der Spieler §6" + p.getName() + " §3hat §61 Punkt gedroppt.");
					p.sendMessage(FFA.ffam.prefix + "§3Du hast §6" + 1 + " §3Punkt gedroppt!");
				}else{
					MySQLStats.addPunkte(killer.getUniqueId().toString(), Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40));
					MySQLStats.removePunkte(p.getUniqueId().toString(), Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40));
					p.sendMessage(FFA.ffam.prefix + "§3Du hast §6" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40) + " §3Punkte gedroppt!");
					killer.sendMessage(FFA.ffam.prefix + "§3Der Spieler §6" + p.getName() + " §3hat §6" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40) + " Punkte gedroppt.");
				}
				Damageable player = (Damageable) killer;
				DecimalFormat f = new DecimalFormat("#0.00");
				double toFormat = ((double)Math.round(player.getHealth()*100))/100;
				if(killer.getLevel() == 4) {
					killer.sendMessage(FFA.ffam.prefix + "§3Du hast einen §6Killstreak von §e5 §3und bekommst §e1 §6Cobweb!");
					killer.getInventory().addItem(new ItemStack(Material.WEB));
				}
				String health = f.format(toFormat/2);
				p.sendMessage(FFA.ffam.prefix + "§cDein Gegner §e" + killer.getDisplayName() + " §chatte noch §e" + health + " §cHerzen.");
				playSound(killer);
				killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 140, 1));
				killer.sendMessage(FFA.ffam.prefix +"§3Du hast den Spieler §e" + p.getDisplayName() + " §3getötet");
				MySQLStats.addKills(killer.getUniqueId().toString(), 1);
				
				killer.setLevel(killer.getLevel()+1);
				//updateScoreboard(killer);		
			}
		}else{
			if(killer != null) {
			if(MySQLStats.getPunkte(p.getUniqueId().toString())/40 < 1) {
				MySQLStats.addPunkte(killer.getUniqueId().toString(), 1);
				MySQLStats.removePunkte(p.getUniqueId().toString(), 1);
				killer.sendMessage(FFA.ffam.prefix + "§3Der Spieler §e" + p.getName() + " §3hat §e1 Punkt gedroppt.");
			}else{
				MySQLStats.addPunkte(killer.getUniqueId().toString(), Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40));
				MySQLStats.removePunkte(p.getUniqueId().toString(), Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40));
				p.sendMessage(FFA.ffam.prefix + "§3Du hast §e" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40) + " §3Punkte gedroppt!");
				killer.sendMessage(FFA.ffam.prefix + "§3Der Spieler §e" + p.getName() + " §3hat §e" + Math.round((float) MySQLStats.getPunkte(p.getUniqueId().toString())/40) + " Punkte gedroppt.");
			}
			Damageable player = (Damageable) killer;
			killer.sendMessage(FFA.ffam.prefix + "§eDu hast den Spieler §c" + p.getDisplayName() + " §egetötet");
			playSound(killer);
			DecimalFormat f = new DecimalFormat("#0.00");
			double toFormat = ((double)Math.round(player.getHealth()*100))/100;
			if(killer.getLevel() == 4) {
				killer.sendMessage(FFA.ffam.prefix + "§3Du hast einen §6Killstreak von §e5 §3und bekommst §e1 §6Cobweb!");
				killer.getInventory().addItem(new ItemStack(Material.WEB));
			}
			String health = f.format(toFormat/2);
			p.sendMessage(FFA.ffam.prefix + "§cDein Gegner §e" + killer.getDisplayName() + " §chatte noch §e" + player.getHealth()/2 + " §cHerzen.");
			MySQLStats.addKills(killer.getUniqueId().toString(), 1);
			killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 140, 1));	
			//updateScoreboard(killer);	
			killer.setLevel(killer.getLevel()+1);
			}else{
				
						
					
				p.sendMessage(FFA.ffam.prefix + "§eDu bist gestorben.");
			}
		}
		JoinQuitListeners.combat.remove(p.getName());
		JoinQuitListeners.combat.remove(p.getName());
		
		
		if(PvP.scheduler.containsKey(p.getName())) {
			PvP.sendTitle(p, null);
			Bukkit.getScheduler().cancelTask(PvP.scheduler.get(p.getName()));
			PvP.scheduler.remove(p.getName());
		
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		
		if(FFA.plugin.getConfig().getString("Spawn." + ".World") != null) {
			
			World w = Bukkit.getWorld(FFA.plugin.getConfig().getString("Spawn." + ".World"));
			double x = FFA.plugin.getConfig().getDouble("Spawn." + ".X");
			double y = FFA.plugin.getConfig().getDouble("Spawn." + ".Y");
			double z = FFA.plugin.getConfig().getDouble("Spawn." + ".Z");
			Location loc = new Location(w, x, y, z);
		e.setRespawnLocation(loc);	
		
				// TODO Auto-generated method stub
				if(KitListeners.save.contains(p.getName())) {
					
					if(KitListeners.kitname.get(p.getName()).equalsIgnoreCase("Spieler")) {
						if(KitListeners.itemstack.containsKey(p.getName())) {
							
							ItemStack[] istack = KitListeners.itemstack.get(p.getName());
							p.getInventory().setContents(istack);
							p.getInventory().setBoots(FFA.ItemStackName1(Material.IRON_BOOTS, 0, 1, null, null));
							p.getInventory().setLeggings(FFA.ItemStackName1(Material.IRON_LEGGINGS, 0, 1, null, null));
							p.getInventory().setChestplate(FFA.ItemStackName1(Material.IRON_CHESTPLATE, 0, 1, null, null));
							p.getInventory().setHelmet(FFA.ItemStackName1(Material.IRON_HELMET, 0, 1, null, null));
						}
					}else if(KitListeners.kitname.get(p.getName()).equalsIgnoreCase("Premium")) {
						if(KitListeners.itemstack.containsKey(p.getName())) {
							
							ItemStack[] istack = KitListeners.itemstack.get(p.getName());
							p.getInventory().setContents(istack);
							
							
							p.getInventory().setBoots(FFA.ItemStackName1(Material.IRON_BOOTS, 0, 1, null, null));
							p.getInventory().setLeggings(FFA.ItemStackName1(Material.IRON_LEGGINGS, 0, 1, null, null));
							p.getInventory().setChestplate(FFA.ItemStackName1(Material.IRON_CHESTPLATE, 0, 1, null, null));
							p.getInventory().setHelmet(FFA.ItemStackName1(Material.IRON_HELMET, 0, 1, null, null));
							
						}
					}else{
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
					}
				}else{
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
				}
			
		

				
			}
		
		

		
		
	}
	
	public void playSound(Player p) {
		if(p.hasPermission("mlg.premium")) {
		if(FFA.plugin.getConfig().getBoolean("Sound." + "Pferde-Tod" + p.getUniqueId().toString()) == true) {
			p.playSound(p.getLocation(), Sound.HORSE_DEATH, 1, 1);
		}else if(FFA.plugin.getConfig().getBoolean("Sound." + "Click" + p.getUniqueId().toString()) == true) {
			p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
		}else if(FFA.plugin.getConfig().getBoolean("Sound." + "Level-Up" + p.getUniqueId().toString()) == true) {
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
		}else if(FFA.plugin.getConfig().getBoolean("Sound." + "Note-Pling" + p.getUniqueId().toString()) == true) {
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
		}
		}
	}

	public ItemStack getItemStack(Material m) {
		
		return new ItemStack(m);
	}
	
	/*public static void updateScoreboard(Player p) {
		PvP.sm = Bukkit.getScoreboardManager();
		 PvP.s = PvP.sm.getNewScoreboard();
		 PvP.object = PvP.s.registerNewObjective("aaa", "bbb");
		 PvP.object.setDisplayName("§aFFA");
		 PvP.object.setDisplaySlot(DisplaySlot.SIDEBAR);
		PvP.s.resetScores(Bukkit.getOfflinePlayer("§3Im Kampf: §aJa"));
		
		PvP.s.resetScores(Bukkit.getOfflinePlayer("§3Kampfende: §c" + PvP.formatted));
		Score Score2 = PvP.object.getScore(Bukkit.getOfflinePlayer("§2Im Kampf: §cNein" ));
		Score2.setScore(2);
		Score score1 = PvP.object.getScore(Bukkit.getOfflinePlayer("§2Kit: §b" + KitListeners.kitname.get(p.getName())));		
		score1.setScore(1);	
		
		
		p.setScoreboard(PvP.s);
	}
	*/

}
