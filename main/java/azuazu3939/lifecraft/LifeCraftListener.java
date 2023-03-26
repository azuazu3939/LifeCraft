package azuazu3939.lifecraft;

import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifeCraftListener implements Listener {

    static List<ItemStack> list;
    public static Map<Player, List<ItemStack>> map;

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {

        if (event.getInventory().getHolder() instanceof LifeCraftHolder) {

            event.setCancelled(true);

            ItemStack item = event.getCursor();
            ItemStack item2 = event.getCurrentItem();
            ItemStack itemStack = MythicMobs.inst().getItemManager().getItemStack("compressed_diamond_block");
            ItemStack itemStack1 = MythicMobs.inst().getItemManager().getItemStack("Compressed_iron_block");
            ItemStack itemStack2 = MythicMobs.inst().getItemManager().getItemStack("Compressed_gold_block");


            //次のInv以降処理
            if (item2 != null && item2.hasItemMeta() &&
                    item2.getType().equals(Material.WHITE_STAINED_GLASS_PANE) &&
                    item2.getItemMeta() != null && item2.getItemMeta().hasCustomModelData() && item2.getItemMeta().getCustomModelData() == 1) {

                if (event.getClickedInventory() != null && itemStack != null && itemStack1 != null && itemStack2 != null &&
                        (event.getClickedInventory().contains(itemStack) || event.getClickedInventory().contains(itemStack1) || event.getClickedInventory().contains(itemStack2))) {

                    list = new ArrayList<>();
                    map = new HashMap<>();
                    for (ItemStack setItem : event.getInventory().getStorageContents()) {

                        if (setItem == null) continue;
                        if (setItem.getType().isAir()) continue;
                        if (setItem.getType().equals(Material.DIAMOND_BLOCK) ||
                                setItem.getType().equals(Material.IRON_BLOCK) ||
                                setItem.getType().equals(Material.GOLD_BLOCK)) {
                            list.add(setItem);
                            map.put((Player) event.getWhoClicked(), list);
                        }
                    }
                    event.getWhoClicked().getPersistentDataContainer().set(new NamespacedKey(LifeCraft.inst(), "open"), PersistentDataType.INTEGER, 1);
                    new CreateInventory().step2((Player) event.getWhoClicked());
                }
            }

            //itemSet処理1
            if (item != null && item.hasItemMeta() && itemStack != null && itemStack1 != null && itemStack2 != null) {
                if (item.isSimilar(itemStack)) {
                    set(item, event.getInventory());

                } else if (item.isSimilar(itemStack1)) {
                    set(item, event.getInventory());

                } else if (item.isSimilar(itemStack2)) {
                    set(item, event.getInventory());
                }

                //itemSet処理2
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

            if (event.getPlayer().getPersistentDataContainer().has(new NamespacedKey(LifeCraft.inst(), "open"), PersistentDataType.INTEGER)) {
                event.getPlayer().getPersistentDataContainer().remove(new NamespacedKey(LifeCraft.inst(), "open"));
                return;
            }
            for (ItemStack item: event.getInventory().getContents()) {

                if (item == null) continue;
                if (item.getType().isAir()) continue;
                if (item.getType().equals(Material.DIAMOND_BLOCK) ||
                        item.getType().equals(Material.IRON_BLOCK) ||
                        item.getType().equals(Material.GOLD_BLOCK)) {
                    event.getPlayer().getInventory().addItem(item);
                }
            }

            if (!map.isEmpty() && map.containsKey((Player) event.getPlayer())) {

                for (ItemStack item: map.get((Player) event.getPlayer())) {
                    event.getPlayer().getInventory().addItem(item);
                }
                map.remove((Player) event.getPlayer());
                list.clear();
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
