package me.devcode.FFAKits.Particel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;


public class ParticelApi {
	
	EnumParticle particletype;
	boolean longdistance;
	Location location;
	float offsetx;
	float offsety;
	float offsetz;
	float speed;
	int amount;
	
	
	public ParticelApi(EnumParticle particletype,	boolean longdistance,	Location location,	float offsetx,	float offsety,	float offsetz,	float speed,	int amount) {
		this.particletype = particletype;
		this.longdistance = longdistance;
		this.location = location;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.offsetz = offsetz;
		this.speed = speed;
		this.amount = amount;
		
	}
	
	public void sendAll() {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance, (float) this.location.getX(), (float) this.location.getY(), (float) this.location.getZ(), this.offsetx, this.offsety, this.offsetz, this.speed, this.amount);
		for(Player all : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	public void sendPlayer(Player player) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance, (float) this.location.getX(), (float) this.location.getY(), (float) this.location.getZ(), this.offsetx, this.offsety, this.offsetz, this.speed, this.amount);
		
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		
	}
	

}
