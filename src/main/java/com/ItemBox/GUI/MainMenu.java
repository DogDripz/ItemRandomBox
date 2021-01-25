package com.ItemBox.GUI;

import com.ItemBox.Utill.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class MainMenu extends GUI {

    public MainMenu(Player p) {
        super(p, "Main GUI", 9 * 5);
    }

    @Override
    protected void init() {
        Random random = new Random();
        Material selectItem = null;
        int persent = random.nextInt()%100;
        if(persent < 45){
            selectItem = Material.GOLDEN_APPLE;
        }else if(persent < 45 + 35){
            selectItem = Material.PAPER;
        }else{
            selectItem = Material.GOLDEN_CARROT;
        }

        Stream.iterate(0, i->i+1)
                .limit(9 * 5).forEach(place -> setItem(" ", null, Material.STAINED_GLASS_PANE,  GUI.LIME, 1, place, "배경", false));
        setItem("close", Arrays.asList("", "닫기", ""), Material.BARRIER, (short)0, 1, 44, "닫기", true);
        setItem("사은품", Arrays.asList("", "메세지", ""), selectItem,  (short)0, 1, 43, "사은품", true);
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
            case "사은품":
                //p.sendMessage("메세지");
                p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
                setItem("수령 완료",null , Material.BARRIER,  (short)0, 1, 43, "수령완료", false);
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
