package me.devcode.FFAKits;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import me.devcode.FFAKits.Commands.Blacklist;
import me.devcode.FFAKits.Commands.FFA_Commands;
import me.devcode.FFAKits.Commands.Fix;
import me.devcode.FFAKits.Commands.Fly;
import me.devcode.FFAKits.Commands.Gamemode;
import me.devcode.FFAKits.Commands.Premium;
import me.devcode.FFAKits.Commands.Save;
import me.devcode.FFAKits.Commands.SetEnable;
import me.devcode.FFAKits.Commands.Stats;
import me.devcode.FFAKits.Commands.Top;
import me.devcode.FFAKits.Commands.Vanish;
import me.devcode.FFAKits.Commands.ping;
import me.devcode.FFAKits.Listeners.BlockedListeners;
import me.devcode.FFAKits.Listeners.DeathListener;
import me.devcode.FFAKits.Listeners.JoinQuitListeners;
import me.devcode.FFAKits.Listeners.KitListeners;
import me.devcode.FFAKits.Listeners.Move;
import me.devcode.FFAKits.Listeners.ParticelSelector;
import me.devcode.FFAKits.Listeners.Premium2;
import me.devcode.FFAKits.Listeners.PvP;
import me.devcode.FFAKits.Listeners.SaveZoneInteract;
import me.devcode.FFAKits.Listeners.SkinsSelector;
import me.devcode.FFAKits.Listeners.SoundSelector;
import me.devcode.FFAKits.Region.Region;

public class FFA extends JavaPlugin {
	
	Logger logger = Logger.getLogger("");
	public static FFA plugin;
	public static FFAMessages ffam = new FFAMessages();
	boolean gesendet = false;
	
	public static File file = new File("plugins/FFAItems", "items.yml");
	public static YamlConfiguration filecfg = YamlConfiguration.loadConfiguration(file);
	
	
	
	
	//Enable
	public void onEnable() {
		
		plugin = this;
		logger.info("Plugin von: " + getDescription().getAuthors());
		logger.info("Plugin aktiviert!");
		onRegister();
		onCRegister();
		loadMySQL();
		
		
	
	getRegion();
		
		autob();
		
		
		
		
	}
	
	public void autob() {
		final String[] messages = {"§3Du bekommst den §5Youtuber §3Rang ab §e1.000 §3aktivien Abonnenten", "§3Unser Teamspeak: §6ts.mlg420.de", "§3Ein Spieler verhält sich nicht richtig? Nutze §e/report"};
		if(gesendet == false) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all != null) {
					all.sendMessage(ffam.prefix + Arrays.asList(messages).get(new Random().nextInt(messages.length)));
				}
			}
			
		gesendet = true;
		autob();
		}else{
		Bukkit.getScheduler().runTaskLater(this, new BukkitRunnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(all != null) {
						all.sendMessage(ffam.prefix + Arrays.asList(messages).get(new Random().nextInt(messages.length)));
					
					}
				}
				autob();
			}
		}, 100*60);
		}
	}
	
	public void getRegion() {
		double x = getConfig().getDouble("SaveZone." + ".X");
		double y = getConfig().getDouble("SaveZone." + ".Y");
		double z = getConfig().getDouble("SaveZone." + ".Z");
		double x2 = getConfig().getDouble("SaveZone." + ".X2");
		double y2= getConfig().getDouble("SaveZone." + ".Y2");
		double z2 = getConfig().getDouble("SaveZone." + ".Z2");
		World w = Bukkit.getWorld(getConfig().getString("SaveZone." + ".World"));
		Location loc = new Location(w, x2, y2, z2);
		Location loc2 = new Location(w, x, y, z);
		Region.addRegion(loc, loc2);
	}
	
	//Disable
		public void onDisable() {
			logger.info("Plugin von: " + getDescription().getAuthors());
			logger.info("Plugin deaktiviert!");
		}
		//Register
		public void onRegister() {
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new BlockedListeners(), this);
			pm.registerEvents(new PvP(), this);
			pm.registerEvents(new SaveZoneInteract() , this);
			pm.registerEvents(new KitListeners(), this);
			pm.registerEvents(new JoinQuitListeners(), this);
			pm.registerEvents(new DeathListener(), this);
			pm.registerEvents(new SkinsSelector(), this);
			pm.registerEvents(new SoundSelector(), this);
			pm.registerEvents(new Move(), this);
			pm.registerEvents(new ParticelSelector(), this);
			pm.registerEvents(new Premium2(), this);
		
		}
		
		//Register Commands
		public void onCRegister() {
			getCommand("ffa").setExecutor(new FFA_Commands());
			getCommand("stats").setExecutor(new Stats());
			getCommand("premium").setExecutor(new Premium());
			getCommand("save").setExecutor(new Save());
			getCommand("top").setExecutor(new Top());
			getCommand("fix").setExecutor(new Fix());
			getCommand("fly").setExecutor(new Fly());
			getCommand("vanish").setExecutor(new Vanish());
			getCommand("gamemode").setExecutor(new Gamemode());
			getCommand("blacklist").setExecutor(new Blacklist());
			getCommand("ping").setExecutor(new ping());
			getCommand("setenable").setExecutor(new SetEnable());
		}
		
		public void loadMySQL() {
			
			new MySQL("localhost", "user", "data", "password");
			MySQL.update("CREATE TABLE IF NOT EXISTS FFAStats(UUID varchar(64), KILLS int, DEATHS int, VERBOT varchar(64), VERBOTUUID varchar(64), PUNKTE int);");
		}
		
		public static ItemStack ItemStackName1(Material m, int s, int i,
				String display, String lorename) {
			ItemStack Item = new ItemStack(m, i, (short) s);
			ItemMeta ItemMeta = Item.getItemMeta();
			ItemMeta.spigot().setUnbreakable(true);
			ArrayList<String> lore = new ArrayList<>();
			if (display == null) {
			} else {
				ItemMeta.setDisplayName(display);
			}
			if (lorename == null) {
			} else {

				lore.add(lorename);
				ItemMeta.setLore(lore);
			}

			
			Item.setItemMeta(ItemMeta);
			return Item;
		}
		/*private String host;
		private int port;
		public void Server(String host, int port) {
			this.port = port;
			this.host = host;
			
		}

		public String parseData(Connection connection) {
			try{
				Socket socket = new Socket();
				OutputStream os;
				DataOutputStream dos;
				InputStream is;
				InputStreamReader isr;
				socket.setSoTimeout(2500);
				socket.connect(new InetSocketAddress(host, port), 2500);
				
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				
				is = socket.getInputStream();
				
				isr = new InputStreamReader(is, Charset.forName("UTF-16BE"));
				
				dos.write(new byte[] {(byte) 0xFE, (byte) 0x01});
				
				
				int packetid = is.read();
				
				if(packetid == -1) 
					System.out.println("Invalid");
					
				
				if(packetid != 0xFF) 
					System.out.println("Invalid");
				
				int length = is.read();
				
				if(length == -1) 
					System.out.println("EOS");
				
				if(length == 0)
				System.out.println("Invalid");
				
				char[] chars = new char[length];
				
				if(isr.read(chars, 0, length) != length) 
					System.out.println("EOS");
				
				String string = new String(chars);
				
				String[] data = string.split("\0");
				
				if(connection == Connection.PLAYERS_ONLINE) {
					System.out.print(Integer.parseInt(data[4]) + "/" + Integer.parseInt(data[5]));
				}
				
				
			}catch(Exception e) {
				return null;
			}
			return host;
		}
		
		public enum Connection {
			PLAYERS_ONLINE, SERVER_MOTD;
		}
		*/
			
		}
		
	


