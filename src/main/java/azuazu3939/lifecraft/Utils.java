package azuazu3939.lifecraft;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

public class Utils {

    public boolean isMythicItem(ItemStack item) {

        if (item == null) return false;
        CompoundTag tag = MythicMobs.inst().getVolatileCodeHandler().getItemHandler().getNBTData(item);

        return tag != null && tag.containsKey("MYTHIC_TYPE");
    }

    @Nullable
    public ItemStack getMythicItem(String string) {

        if (MythicMobs.inst().getItemManager().getItem(string).isPresent()) {
            return MythicMobs.inst().getItemManager().getItemStack(string);
        }
        return null;
    }

    @Nullable
    public String getMythicId(ItemStack item) {

        if (item == null) return null;
        CompoundTag tag = MythicMobs.inst().getVolatileCodeHandler().getItemHandler().getNBTData(item);

        if (tag != null && tag.containsKey("MYTHIC_TYPE")) {
            return tag.getString("MYTHIC_TYPE");
        }
        return null;
    }

    public void setItem(ItemStack setItem, ItemStack replacedItem) {

        if (setItem == null || !setItem.hasItemMeta()) {
            replacedItem.setType(Material.AIR);
        } else {

            ItemMeta meta = setItem.getItemMeta();
            Material mate = setItem.getType();
            if (meta != null) {
                replacedItem.setType(mate);
                replacedItem.setItemMeta(meta);
            }
        }
    }
}
