package azuazu3939.lifecraft;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
import io.netty.channel.Channel;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

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

    @NotNull
    public static Channel getChannel(@NotNull Player player) {
        // channel field is set to non-null value after #channelActive is called
        return Objects.requireNonNull(((CraftPlayer) player).getHandle().playerConnection.networkManager.channel, "inactive channel");
    }
}
