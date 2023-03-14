package azuazu3939.lifecraft;

import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LifeCraftListener implements Listener {

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {

        if (event.getInventory().getHolder() instanceof LifeCraftHolder) {

            event.setCancelled(true);

            ItemStack item = event.getCursor();
            ItemStack item2 = event.getCurrentItem();
            ItemStack itemStack = MythicMobs.inst().getItemManager().getItemStack("compressed_diamond_block");
            ItemStack itemStack1 = MythicMobs.inst().getItemManager().getItemStack("Compressed_iron_block");
            ItemStack itemStack2 = MythicMobs.inst().getItemManager().getItemStack("Compressed_gold_block");

            if (item != null && item.hasItemMeta() && itemStack != null && itemStack1 != null && itemStack2 != null) {
                if (item.isSimilar(itemStack)) {
                    set(item, event.getInventory());

                } else if (item.isSimilar(itemStack1)) {
                    set(item, event.getInventory());

                } else if (item.isSimilar(itemStack2)) {
                    set(item, event.getInventory());
                }

            } else if (item2 != null && item2.hasItemMeta() && itemStack != null && itemStack1 != null && itemStack2 != null) {
                if (item2.isSimilar(itemStack)) {
                    set(item2, event.getInventory());

                } else if (item2.isSimilar(itemStack1)) {
                    set(item2, event.getInventory());

                } else if (item2.isSimilar(itemStack2)) {
                    set(item2, event.getInventory());
                }
            }
        }
    }

    @EventHandler
    public void onDrag(@NotNull InventoryDragEvent event) {

        if (event.getInventory().getHolder() instanceof LifeCraftHolder) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent event) {

        if (event.getInventory().getHolder() instanceof LifeCraftHolder) {

            for (ItemStack item: event.getInventory().getStorageContents()) {

                if (item.getType().equals(Material.DIAMOND_BLOCK) ||
                        item.getType().equals(Material.IRON_BLOCK) ||
                        item.getType().equals(Material.GOLD_BLOCK)) {
                    event.getPlayer().getInventory().addItem(item);
                }
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
                break;
            }
        }
    }
}
