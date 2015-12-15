package me.devcode.FFAKits.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.devcode.FFAKits.FFA;

public class SkinsSelector implements Listener{
	
	
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§6Gadgets")) {
			if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, "§7Wähle einen Skin aus:");
				ItemStack fs = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				
				SkullMeta fsmeta = (SkullMeta) fs.getItemMeta();
				
					fsmeta.setOwner(Bukkit.getOfflinePlayer("12ms").getName());
				fsmeta.setDisplayName("§e12ms");
				fs.setItemMeta(fsmeta);
				
				ItemStack ss = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				
				SkullMeta ssmeta = (SkullMeta) ss.getItemMeta();
				
					ssmeta.setOwner(Bukkit.getOfflinePlayer("_Rainbow_").getName());
				ssmeta.setDisplayName("§eRainbow");
				ss.setItemMeta(ssmeta);
				
				ItemStack ts = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta tsmeta = (SkullMeta) ts.getItemMeta();
				tsmeta.setOwner(Bukkit.getOfflinePlayer("Rippeax").getName());
				tsmeta.setDisplayName("§eRippeax");
				ts.setItemMeta(tsmeta);
				
				ItemStack fs1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta fsmeta1 = (SkullMeta) fs1.getItemMeta();
				fsmeta1.setOwner(Bukkit.getOfflinePlayer("Riken_").getName());
				fsmeta1.setDisplayName("§eRiken");
				fs1.setItemMeta(fsmeta1);
				
				ItemStack fs2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta fsmeta2 = (SkullMeta) fs2.getItemMeta();
				fsmeta2.setOwner(Bukkit.getOfflinePlayer("YourePingu").getName());
				fsmeta2.setDisplayName("§eYourePingu");
				fs2.setItemMeta(fsmeta2);
				
				ItemStack defaulti = new ItemStack(Material.BARRIER);
				ItemMeta defaultm =  defaulti.getItemMeta();
				List<String> list = new ArrayList<String>();
				list.add("§3Entferne deinen §eSkin");
				defaultm.setDisplayName("§cEntfernen");
				defaultm.setLore(list);
				defaulti.setItemMeta(defaultm);
			
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
				ItemMeta glassm =  glass.getItemMeta();
				glassm.setDisplayName("§b???");
				glass.setItemMeta(glassm);
				
				
				
				inv.setItem(0, fs);
				inv.setItem(1, ss);
				inv.setItem(2, ts);
				inv.setItem(3, fs1);
				inv.setItem(4, fs2);
				inv.setItem(5, glass);
				inv.setItem(6, glass);
				inv.setItem(7, glass);
				inv.setItem(8, defaulti);
				e.getWhoClicked().openInventory(inv);
				}
			}
		}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick1(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("§7Wähle einen Skin aus:")) {
			String value = "";
			String signature = "";
			boolean b = false;
			final Player p = (Player) e.getWhoClicked();
			if(e.getCurrentItem().getType() == Material.AIR) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§b???")){
			e.setCancelled(true);
			p.sendMessage(FFA.ffam.prefix + "§cDu kannst als §5Youtuber §cauf unserem §eTeamspeak §cdeinen §6Skin§c anfordern.");
			}else{
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§e12ms")) {
				e.setCancelled(true);
				 value = "eyJ0aW1lc3RhbXAiOjE0NDkyNzMyOTYzMDAsInByb2ZpbGVJZCI6IjY1ZWJjNWM3YTY3YzQ3NGU4NDBlNDQzMTllZTM4OTU3IiwicHJvZmlsZU5hbWUiOiIxMm1zIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZjgyNjcxZTdiOTY4NjZlZmM0MWVkMTcyZjUyYWZiNWIxZTFhNDFmMjY1MWFkZTIwZTA1MjI5MzFjZGYyZjAifX19";
				 signature = "pPajxY+v7a8u6KlMJ5rf107y6OEUWG8HNnv0ZpjWlyZRHzdG+FSbFBs3fMYbfeCH8zfzQFeFzaU13QXSclQDcpW6C/6AaQu94tvmsyCTdPsudC+8JYigLGiXsrW8/9D1HlE2896s7OFBuI5z03PAhhEGkyBQMPuyIS9Fb+r47vVb1fQT+m6NxMnyvdOVFwBJkqtgP8qMdLROL+eHrCyoH2HCAO4qvan/rCH+AVHndkjmVjmMDXCqsXVueUCGqoKD/boAkU5PLK8RHMpdBNj9fc7M8QqGjHjkCBby1zO7UJxk/mVARBdD80Jg2gt8yf9VmG1iSfCqWLceKHzLrDC7RrNlOejeilT0tnhOhXwVa9TYqrQaSvN3u73y94lQ43g6Wc5ilYy+1C++2DnVlco/Tej17s4TooUcVbMNXdw6Hw9R3FMDazUuBU/KwSrZGRUoGiJngW0JG2OD6QodVQy6aNista5T+k2fL/u3BF2BxGuq8vDLM+fYDQo1PUaRytzk4SOdlHVkIUlXSDLrWGCbl+2FD1GFwZ+imjIaQY6kTRFRwO6E8d2lN4gBwCZ0D2emV2fOVMr/PeJSOuSpL9gyHrmZDHROc0TQbV5Aj4UqrFbMwlL0LnYeBYy+RxmohAMPNZVHltDszEh1EgItOgcKRDa/fijX5WSFHFimfOFohjo=";
				b = true;
				
				
				p.sendMessage(FFA.ffam.prefix + "§3Du musst neu connecten, um den §e12ms-Skin §3zu erhalten!");
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§eRainbow")) {
				e.setCancelled(true);
				 value = "eyJ0aW1lc3RhbXAiOjE0NDkzNDI2NjQyMjEsInByb2ZpbGVJZCI6ImZmMzBhODgxOGRlYzRiMmZiY2ZlZjljMDUzZDBiNWVkIiwicHJvZmlsZU5hbWUiOiJfUmFpbmJvd18iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkMTcwMmQxOGQxOThmMmIxMzA1ODQ1MzI3OTQyMzA1OGNmOTA3ZjMxNjM4NzZkYzg4YjBlMjJkMTAifX19";
				 signature = "hsHvGA/K1wURgxNb9RDGt+XfLLxQQygVSwhSSTvAFfjh4dpXzB1oSg+lSz1ERjBdmK0PjnTtyk7QSe64F3hyemVCwzHE1SmxiZnEIirS+oI2ViK6WMkKYsAxvmLCXKWYalyliDfAAk0xTlIydjm2GL/tLh8pPGyrM+SKGij6WXJdVNLRFJK4yWXi0ut1XmT3sQWoO9GVONOpCmu8lyX+TyoUYg0/1FG8Zp9jWr9+rGqbEeVO/pOkboEKm3WCAV2tnxZ653n8y5lQPT3xGOUp+RIrtBeCmwjbS8fs4k4Kt6tb2SUE/7mYvU4lxRX/AE3jSv3T1KU8oPvWyfDGXztrim+oSLgrwFFAHJK5Cq6UphMJblO5N7KYmPymPIjbnGhxOivuuaxRwYMBXKh+yxPRoTioNHgenHliIsmCDdTNshzVBuqaiH0p3cw6x1mKhZ6uaEqMGx7B2xFl6ftiX1L/jhHThUR4ZYDdmdm2mRey9H89yTV85Ek9wwSpHHFHzYkyyorAoTZL0qiip/q3Wswp1D4ZA56OvH+5kpNdvFIFOBJB6zK+vtaTiWgHZaK4MCawJf208yHJ2kxb0+aUbnWAvHb0nLwdOdHhWcNhdQSI0fMeRgufJNuTrnW067gcO4iabfmvUxSvZIVRS32IdxhhlGkbhJzbgf7OmKjL0mDpcxg=";
				b = true;
				
				
				p.sendMessage(FFA.ffam.prefix + "§3Du musst neu connecten, um den §eRainbow §eSkin §3zu erhalten!");
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§eRippeax")) {
				e.setCancelled(true);
				value = "eyJ0aW1lc3RhbXAiOjE0NDkyNzQ5NTAwMTYsInByb2ZpbGVJZCI6IjdlMmMzNDgyYzNlZTQxNGFiODYyYWVhZTIxNWM2YjdiIiwicHJvZmlsZU5hbWUiOiJSaXBwZWF4Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xMWQ1OTZjYzk4YTM4MmJhYzExZWE0N2FiYzRmY2E2MGQzODFiODRiYTBmYmJiYTJmYmYxNmIxNWJlOWQ4OCJ9fX0=";
				signature = "CaBz6oTkfDGnSciXf1BaaKXspmf8Hio0TuEh9/FMQ3P5FvxAlrA+WRaIrNGxw/JfxjqEz5UwU9LdiFRJLziWiR2tPVHfpODKwL20Dz+hRoyevXFFgJH+hTZA0sH8SudG5R5hSAPc8d2dAbznDC9Fuv4alATbBuQVZJ0KkFhHl2lOkID9UPbDvAP3pKFeSqVkzpS+0DAm1BLyqrmQylYZ4hZAvhtGQR9V34jeOvzmrXrYEoPLCMvzaHdM+xp2aF1qJXcxq9BsdqZIq9+tqGpCXcWh+VAWtnajWYTTMw7o0x4a7XFFVGrFKSEatQC0jzSouswbv+hBZIPm2WiGl7S70OZKDe0+zLl+gWZvqng+S3DJxajXD8gY8MKvADsay1vhSUZenC8Q7oWwdbzsIlymap4DpL9wEk0Nm49k6OOgtniPk7Lfr+dOq7dbuO0zWnasVJJyu84X5qPiKd8iAxO4W/KxkNMPGa1CpiKNNwzeD25jHrZqyjjGcrROjSu8Ise11pVCvcnLnCQrLxm8U5xu9LHKw9wZtrX27R5Okk4lB/I2Q5ugCXp1Ws98/IL9o2VZ7Hoxw2xbRYv/kNSs28TtOM4zVvvyuhZVcN3ZgMhiwlkq9y52Elt9KAeJuu3IQaDjkidhuL0vvo0KVR3F0FEYhLUUklaqXOf6e2UpLhlptQ4=";
				p.sendMessage(FFA.ffam.prefix + "§3Du musst neu connecten, um den §cRippeax-Skin §3zu erhalten!");
				b = true;
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§eRiken")) {
				e.setCancelled(true);
				value = "eyJ0aW1lc3RhbXAiOjE0NDk0Mjg3NTkxODksInByb2ZpbGVJZCI6IjAyNmMzMTY3Mjk0ZDQ4NzhiNmY0NzVlZDM2NTI1MWZhIiwicHJvZmlsZU5hbWUiOiJSaWtlbl8iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y4ZWQzNWQwNzhiNmJjNmI2YThlOTc5YjJhMmM1ZWRhOTliYWQwOWEyZDMzYWJlNzEwYTA1OWIxYjg4NDZiZGQifX19";
				signature = "bPSBKabSeXmrWPbyaC56ueOVGIvqH7PT785fYlazPGXu00Enj/o+yAcRE4Phuw5zc7meAEDi4GVjS9sBP7vt+TMsg9hpVkXoKZ0rMZga4Qch5g/GwmRq+hKYnyCj26q7ViB4TmSUM6t+ISOkAvu3FdYiiXxcxDphn1LIMAluGBoI2EcPDuSSNV6ctVVZpd2/qQreIWbM3SB6EdeOavZro0U3fn5am0/NX+avaCSX2pCywRpOCzYfkA/urRaMq5fHkcWbOXI3VZg0bimBTycSmnrw8qLLDn4vXNDkI2SHFva3v3IMYZLUAnLwoX8KEBCHLisvI3mfIjAVTMVKiyt+Jbrc8r5Pev1UxxHqp/2o8roj7A5ifZCTnPbBdAw51agZTdnrOhORK2udvDxcU6gVIwMJLz9SS7aTxog+Y7P2IHme8VGo/dXqecqxub7R5jlpEnLWwyWdqW98QTi7uOTWsyeclK9UPMd0Jsqz5kjc2rKWRH53kT467y6EwE+lMwEims8H1BeN2dZ464szSw8MXbdigpgCoPZurWx8YLoNUpoiohDnwGkucQFRtKOTiF/+86LdHdjnHhzoBnCGkJkhBdK6otpkEAgZhio55oU2R8ZiV0onZLH76y3AmDzo82SD5scLG4Sy8Xn6oCE/0jKReBm000IJA6I2Pu10+3dcRNA=";
				p.sendMessage(FFA.ffam.prefix + "§3Du musst neu connecten, um den §cRiken-Skin §3zu erhalten!");
				b = true;
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§eYourePingu")) {
				e.setCancelled(true);
				value = "eyJ0aW1lc3RhbXAiOjE0NDk1MjA0MTU2MTMsInByb2ZpbGVJZCI6IjhmYjAwZTQ4Mzc1OTQ1Mzc5ODRjMGJkMTgzYjM5MzgzIiwicHJvZmlsZU5hbWUiOiJZb3VyZVBpbmd1Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zNWU4NmE4ZGYxM2YzY2VlZGI5MGJhOTNmNGJlMzRjM2FjZTgyZGIzYjc0OTAyYTFmYzY2ODU3MjRjOTAzMiJ9fX0=";
				signature = "a6DpDvd2qw8nQYEaiMqMcwATxGLEfqL1Piqrt/0su/IiUMSqSUmW/9pTnZqBIoGv8GYLq5RGbT/sgHezmDF5KXqZjivp4ln37cwYPbZ3vjWDKStoKgU8wgHt+0o5wHrY5eZBAeJoILgApGEW9+qSAX+uOg8CfuwZsJOldZiJqR+MV8wxl5WpPsTgFiy+aGjVTN5RhkIGZ1IeCNXGgUcIoWhszJOLhLVT43Me2X/wzfEhX/D2gH4G9L0bfNkzEsIHmUK/ixDOjXkbEaOMvd+MbXvlSmPJ/Ez8TGD3Cj1a4VPEnk5UmUosTLIG7KxWtSUn9zJIbPG1pQM3uh5uIvTKxUvDgbeQZ02mEk+g7hu1jMLtRvMGNUEs9Wpsln08hwsuejyKtqYzDvB7F5bn1rcT5n6rC0uj6DrSA0TOZ3NpfZ0PdWxDfH2JpsPDBZ/z2KDLrL+svZ7i8q2+5Mqq65VHQOnmlpjVRwY8zMpJebMpVMQld14fV5fdL5Xxe9Y4Nw+12TSoA2RNeyrVR6KkYM6Z81e1vDq87U9u1xwBkkV7vxI+Su81RhmiaNoZZYgG6D+O6Qcp0VYfc8tvo6qL3rT1dnDWmLxMl0/VEEgAv6/WO7/hwZHnxGwFp8a70rc+twosQVN6qB/JXoLlVcQduUrvh3N6Uv6RDNEkEGYwMirSFyM=";
					p.sendMessage(FFA.ffam.prefix + "§3Du musst neu connecten, um den §cYourePingu-Skin §3zu erhalten!");
				b = true;
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§cEntfernen")) {
				e.setCancelled(true);
				value = null;
				signature = null;
				b = false;
				p.sendMessage(FFA.ffam.prefix + "§cDu musst neu connecten, um den §eEvent-§eSkin §czu erhalten!");
			}
			FFA.plugin.getConfig().set("Skin." + ".Boolean" + p.getUniqueId().toString(), b);
			FFA.plugin.getConfig().set("Skin." + ".Value" + p.getUniqueId().toString(), value);
			FFA.plugin.getConfig().set("Skin." + ".Signature" + p.getUniqueId().toString(), signature);
			FFA.plugin.saveConfig();
			}
		}
		}


}
