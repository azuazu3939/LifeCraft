package azuazu3939.lifecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CraftListener implements Listener {

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {

        if (event.getInventory().getHolder() instanceof CraftHolder) {

            event.setCancelled(true);
            ItemStack item = event.getCursor();
            ItemStack item2 = event.getCurrentItem();

            //itemSet処理1
            if (item != null && item.hasItemMeta() && item.getItemMeta() != null) {

                List<String> list = item.getItemMeta().getLore();
                if (list == null) return;
                for (String s: list) {
                    if (s.contains("[LifeCraft]")) {
                        set(item, event.getInventory());
                    }
                }

                //itemSet処理2
            } else if (item2 != null && item2.hasItemMeta() && item2.getItemMeta() != null) {

                List<String> list = item2.getItemMeta().getLore();
                if (list == null) return;
                for (String s: list) {
                    if (s.contains("[LifeCraft]")) {
                        set(item2, event.getInventory());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrag(@NotNull InventoryDragEvent event) {

        if (event.getInventory().getHolder() instanceof CraftHolder) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent event) {

        if (event.getInventory().getHolder() instanceof CraftHolder) {

            if (!LifeCraftListener.map.isEmpty() && LifeCraftListener.map.containsKey((Player) event.getPlayer())) {

                for (ItemStack item: LifeCraftListener.map.get((Player) event.getPlayer())) {
                    event.getPlayer().getInventory().addItem(item);
                }
                LifeCraftListener.map.remove((Player) event.getPlayer());
                LifeCraftListener.list.clear();
            }
        }
    }

    public void set(ItemStack item, Inventory inv) {

        if (item == null) return;
        for (int i = 10; i <= 16; i++) {
            if (inv.getItem(i) == null && item.getAmount() >= 1) {

                ItemStack itemStack = new ItemStack(item);
                itemStack.setAmount(1);
                inv.setItem(i, itemStack);
                item.setAmount(item.getAmount() -1);
                return;
            }
        }
        for (int i = 19; i <= 25; i++) {
            if (inv.getItem(i) == null && item.getAmount() >= 1) {

                ItemStack itemStack = new ItemStack(item);
                itemStack.setAmount(1);
                inv.setItem(i, itemStack);
                item.setAmount(item.getAmount() -1);
                return;
            }
        }
        for (int i = 28; i <= 34; i++) {
            if (inv.getItem(i) == null && item.getAmount() >= 1) {

                ItemStack itemStack = new ItemStack(item);
                itemStack.setAmount(1);
                inv.setItem(i, itemStack);
                item.setAmount(item.getAmount() -1);
                return;
            }
        }
        for (int i = 37; i <= 43; i++) {
            if (inv.getItem(i) == null && item.getAmount() >= 1) {

                ItemStack itemStack = new ItemStack(item);
                itemStack.setAmount(1);
                inv.setItem(i, itemStack);
                item.setAmount(item.getAmount() -1);
                return;
            }
        }
    }
}
