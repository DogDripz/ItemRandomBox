package com.ItemBox.AS;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;//뿌잉뿌잉//
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.ItemBox.GUI.ExampleGui;


public class AS implements Listener {
	JavaPlugin plugin;

public AS(JavaPlugin plugin) {
	this.plugin = plugin;
}
	
@SuppressWarnings("deprecation")
@EventHandler
public void onPlayerBoxing(PlayerInteractEvent a) 
{
	Player player = a.getPlayer();
	Location loc = player.getLocation();
	Inventory inv = player.getInventory();
	Action ax = a.getAction();
	ExampleGui ex = new ExampleGui();
	if(ax == Action.RIGHT_CLICK_AIR && a.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Box")) 
	{
	inv.removeItem(player.getItemInHand());
	ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);
	ArmorStand stand2 = loc.getWorld().spawn(loc, ArmorStand.class);
	stand2.setVisible(false);
	stand.setVisible(false);
	stand.setGravity(false);
	stand.setHelmet(new ItemStack(Material.GLOWSTONE));
	int animate = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin ,new Animate(stand),0,1);
	
	new BukkitRunnable( ) {
		@Override
		public void run() 
		{
			Bukkit.getScheduler().cancelTask(animate);
			stand.remove();
			Firework firework = (Firework) stand2.getLocation().getWorld().spawn(stand2.getLocation(), Firework.class );
			stand2.remove();
			FireworkMeta fm = firework.getFireworkMeta();
			fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(Type.CREEPER).withColor(Color.AQUA).withFade(Color.BLUE).build());
			fm.setPower(3);
			firework.setFireworkMeta(fm);
			ex.openInventory(player);
		}
	}.runTaskLater(plugin,100);
			   
			}
		}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemPlace(BlockPlaceEvent e)
		{
		if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Box")) {
			e.setCancelled(true);
		}
	}
}	   
	

