package com.ItemBox.GUI;

import com.ItemBox.Utill.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.stream.Stream;

public class MainMenu extends GUI {

    public MainMenu(Player p) {
        super(p, "Main GUI", 9 * 5);
    }

    @Override
    protected void init() {
        Stream.iterate(0, i->i+1)
                .limit(9*5).forEach(integer -> setItem(" ", null, Material.STAINED_GLASS_PANE,  GUI.LIGHT_BLUE, 1, integer, "배경", false));
        setItem("close", Arrays.asList("", "닫기", ""), Material.BARRIER, (short)0, 1, 44, "닫기", true);
        setItem("메세지", Arrays.asList("", "메세지", ""), Material.ANVIL,  (short)0, 1, 43, "메세지", true);
        setItem("메뉴1", Arrays.asList("", "메뉴1", ""), Material.PAPER,  (short)0, 1, 9 + 0, "메뉴1", true);
        setItem("메뉴2", Arrays.asList("", "메뉴2", ""), Material.PAPER,  (short)0, 1, 9 + 1, "메뉴2", true);
        setItem("메뉴3", Arrays.asList("", "메뉴3", ""), Material.PAPER,  (short)0, 1, 9 + 2, "메뉴3", true);
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
            case "메세지":
                p.sendMessage("메세지");
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
