package azuazu3939.lifecraft.net.minecraft.server;

import azuazu3939.lifecraft.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getLogger;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();
        try {
            Utils.getChannel(player).pipeline()
                    .addBefore("packet_handler", "lifecraft", new PacketListener(player));
            getLogger().info("[LifeCraft] Injected packet handler for MCLove32");
        } catch (Exception e) {
            getLogger().warning("Failed to inject channel handler to player " + player.getName() + "エラー: " + e);
        }
    }
}
