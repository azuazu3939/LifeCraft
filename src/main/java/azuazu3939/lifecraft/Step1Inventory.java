package azuazu3939.lifecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Step1Inventory {

    public void step1(Player player) {

        Inventory inv = Bukkit.createInventory(new LifeCraftHolder(), 27, ChatColor.translateAlternateColorCodes('&', "&b&l圧縮ダイヤ&f,&7&l圧縮鉄&f,&6&l圧縮金&fのブロックのみ可能。"));

        for (int i = 0; i <= 9; i++) {inv.setItem(i, new CreateItem().getBackPane());}
        for (int i = 17; i <= 25; i++) {inv.setItem(i, new CreateItem().getBackPane());}
        inv.setItem(26, new CreateItem().getNextStep2());

        player.openInventory(inv);
    }
}
