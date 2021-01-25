package com.ItemBox.GUI;

import com.ItemBox.Utill.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.stream.Stream;

public class Menu1GUI extends GUI {
    public Menu1GUI(Player p) {
        super(p, "Menu1 GUI", 9 * 5);
    }

    @Override
    protected void init() {
        Stream.iterate(0, i->i+1)
                .limit(9*5).forEach(integer -> setItem(" ", null, Material.STAINED_GLASS_PANE,  GUI.LIME, 1, integer, "배경", false));
        setItem("close", Arrays.asList("", "닫기", ""), Material.BARRIER, (short)0, 1, 44, "닫기", true);
        setItem("Home", Arrays.asList("", "홈으로", ""), Material.WOOD_DOOR,  (short)0, 1, 43, "HOME", true);
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);
        String btn = getValue(e.getRawSlot());
        if (btn == null)
            return;
        switch (btn) {
            case "닫기":
                p.closeInventory();
                break;
            case "HOME":
                new MainMenu(p);
                break;
            case "메뉴1":
                new Menu1GUI(p);
                break;
            case "메뉴2":
                new Menu2GUI(p);
                break;
            case "메뉴3":
                new Menu3GUI(p);
                break;
            default:
                break;
        }
    }
}
