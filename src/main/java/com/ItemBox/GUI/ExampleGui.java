package com.ItemBox.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ExampleGui implements Listener {
	private final Inventory main; 
	private final String MAIN_TITLE = "Main GUI"; 
	private final Inventory menu1Gui;
	private final String MENU1_TITLE = "MENU 1 GUI";
	private final ItemStack home; 
	private final ItemStack close;
	private final ItemStack menu1;

	public ExampleGui() {
		main = Bukkit.createInventory(null, 9 * 3, MAIN_TITLE); 
																
		menu1Gui = Bukkit.createInventory(null, 9 * 3, MENU1_TITLE);
		close = createGuiItem(Material.BARRIER, "CLOSE"); 
		home = createGuiItem(Material.DIRT, "§bHOME");
		menu1 = createGuiItem(Material.APPLE, "§aMENU1");
		initializeItems(); 
	}

	
	public void initializeItems() {
		main.setItem(26, close); 
		main.setItem(13, menu1);
		menu1Gui.setItem(26, close);
		menu1Gui.setItem(25, home);
	}

	
	
	protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(name);

		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;
	}

	
	public void openInventory(final HumanEntity ent) {
		ent.openInventory(main);
	}

	
	public void openInventory(final HumanEntity ent, Inventory inventory) {
		ent.openInventory(inventory);
	}

	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) { 
			Player p = (Player) e.getWhoClicked(); 
			final ItemStack clickedItem = e.getCurrentItem(); 
			if (e.getView().getTitle() != MAIN_TITLE && e.getView().getTitle() != MENU1_TITLE)
				return; 
			e.setCancelled(true);
			if (clickedItem == null || clickedItem.getType() == Material.AIR)
				return; 
			else if (clickedItem.isSimilar(close)) { 
				p.closeInventory(); 
				p.sendMessage("닫았습니다."); 
			} else if (clickedItem.isSimilar(home)) {
				sendMessageInventory(p, "다시 열었습니다."); 
			} else if (clickedItem.isSimilar(menu1)) {
				sendMessageInventory(p, "메뉴 1", menu1Gui); 
			}
		} else {
			System.out.println("fail"); 
		}

	}

	private void sendMessageInventory(Player player, String message, Inventory inventory) {
		player.closeInventory();
		player.sendMessage(message);
		openInventory(player, inventory);
	}

	private void sendMessageInventory(Player player, String message) {
		player.closeInventory();
		player.sendMessage(message);
		openInventory(player);
	}

	@EventHandler
	public void onInventoryClick(InventoryDragEvent e) {
		if (e.getView().getTitle() != MAIN_TITLE && e.getView().getTitle() != MENU1_TITLE) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			p.updateInventory();
		}
	}
}