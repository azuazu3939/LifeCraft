package azuazu3939.lifecraft.net.minecraft.server;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_15_R1.EnumHand;
import net.minecraft.server.v1_15_R1.PacketPlayInUseItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PacketListener extends ChannelDuplexHandler {

    private final Player player;
    public static final Map<Player,Boolean> map = new HashMap<>();

    public PacketListener(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof PacketPlayInUseItem) {
            PacketPlayInUseItem packet = (PacketPlayInUseItem) msg;

            Set<Material> set = new HashSet<>();
            set.add(Material.LAVA);
            set.add(Material.AIR);
            set.add(Material.WATER);

            Block block = player.getTargetBlock(set, 5);
            if (!packet.b().equals(EnumHand.MAIN_HAND) || !(block.getType() == Material.IRON_BLOCK || block.getType() == Material.GOLD_BLOCK)) {
                super.channelRead(ctx, msg);
                return;
            }
            if (map.containsKey(player)) {
                super.channelRead(ctx, msg);
            } else {
                player.sendMessage(ChatColor.GREEN + "/blockingで、鉄・金ブロックのmcMMOスキルを発動できるようにします。");
            }
            return;
        }
        super.channelRead(ctx, msg);
    }
}
