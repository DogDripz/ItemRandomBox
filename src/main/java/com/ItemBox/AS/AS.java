package com.ItemBox.AS;

import com.ItemBox.GUI.MainMenu;
import com.ItemBox.Utill.GUI;
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
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.ItemBox.GUI.ExampleGui;

import java.util.Optional;


public class AS implements Listener {
    JavaPlugin plugin;

    public AS(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBoxing(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Inventory inv = player.getInventory();
        Action action = event.getAction();
        Optional<ItemStack> maybeItem = Optional.ofNullable(event.getItem());
        if (action == Action.RIGHT_CLICK_AIR && maybeItem.map(ItemStack::getItemMeta).map(ItemMeta::getDisplayName).map(s -> s.equals("Box")).orElse(false)) {
            event.setCancelled(true);
            maybeItem.get().setAmount(maybeItem.get().getAmount()-1);
            eventItemRandomBox(loc, player, new ExampleGui());

        }
    }

    
    @EventHandler
    public void onItemPlace(BlockPlaceEvent event) {
        Optional<ItemStack> maybeItem = Optional.ofNullable(event.getItemInHand());
        if (maybeItem.map(ItemStack::getItemMeta).map(ItemMeta::getDisplayName).map(s -> s.equals("Box")).orElse(false)) {
            event.setCancelled(true);
            eventItemRandomBox(event.getBlock().getLocation(), event.getPlayer(), new ExampleGui());
        }
    }

    private void eventItemRandomBox(Location loc, Player player, ExampleGui gui){
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);
        ArmorStand stand2 = loc.getWorld().spawn(loc, ArmorStand.class);
        stand2.setVisible(false);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setHelmet(new ItemStack(Material.GLOWSTONE));
        int animate = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Animate(stand), 0,1);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().cancelTask(animate);
                stand.remove();
                Firework firework = (Firework) stand2.getLocation().getWorld().spawn(stand2.getLocation(), Firework.class);
                stand2.remove();
                FireworkMeta fm = firework.getFireworkMeta();
                fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(Type.CREEPER).withColor(Color.AQUA).withFade(Color.BLUE).build());
                fm.setPower(3);
                firework.setFireworkMeta(fm);
                new MainMenu(player);
                //gui.openInventory(player);
            }
        }.runTaskLater(plugin, 100);
    }

    @EventHandler
    public void guiClick(InventoryClickEvent e) {
        GUI gui = GUI.getGUI((Player) e.getWhoClicked());
        if(gui != null) gui.onClick(e);
    }

    @EventHandler
    public void guiClose(InventoryCloseEvent e) {
        GUI gui = GUI.getGUI((Player) e.getPlayer());
        if(gui != null) gui.closeGUI(e);
    }
}	   
	

