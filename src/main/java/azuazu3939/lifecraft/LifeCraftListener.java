package azuazu3939.lifecraft;

import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class LifeCraftListener implements Listener {

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {

        if (event.getInventory().getHolder() instanceof LifeCraftHolder) {

            ItemStack item = event.getCursor();
            if (event.getCurrentItem() != null || item == null || !item.hasItemMeta()) return;

            ItemMeta meta = item.getItemMeta();
            if (meta == null) return;

            ItemStack itemStack = MythicMobs.inst().getItemManager().getItemStack("compressed_diamond_block");
            ItemStack itemStack1 = MythicMobs.inst().getItemManager().getItemStack("Compressed_iron_block");
            ItemStack itemStack2 = MythicMobs.inst().getItemManager().getItemStack("Compressed_gold_block");

            if (itemStack != null && itemStack.hasItemMeta() && item.equals(itemStack)) {
                item.setAmount(item.getAmount() - 1);
                new Utils().setItem(item, null);

            } else if (itemStack1 != null && itemStack1.hasItemMeta() && item.equals(itemStack1)) {
                item.setAmount(item.getAmount() - 1);
                new Utils().setItem(item, null);

            } else if (itemStack2 != null && itemStack2.hasItemMeta() && item.equals(itemStack2)) {
                item.setAmount(item.getAmount() - 1);
                new Utils().setItem(item, null);

            }

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(@NotNull InventoryDragEvent event) {

        if (event.getInventory().getHolder() instanceof LifeCraftHolder) {
            event.setCancelled(true);
        }
    }
}
