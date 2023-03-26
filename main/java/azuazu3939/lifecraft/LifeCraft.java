package azuazu3939.lifecraft;

import azuazu3939.lifecraft.net.minecraft.server.BlockingCommand;
import azuazu3939.lifecraft.net.minecraft.server.Listeners;
import azuazu3939.lifecraft.net.minecraft.server.PacketListener;
import io.netty.channel.ChannelPipeline;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LifeCraft extends JavaPlugin {

    private static LifeCraft craft;
    public LifeCraft() {craft = this;}
    public static LifeCraft inst() {return craft;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("LifeCraft")).setExecutor(new LifeCraftCommand());
        Objects.requireNonNull(getCommand("blocking")).setExecutor(new BlockingCommand());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new LifeCraftListener(), this);
        pm.registerEvents(new CraftListener(), this);
        pm.registerEvents(new Listeners(), this);

        for (Player player: Bukkit.getOnlinePlayers()) {
            try {
                Utils.getChannel(player).pipeline()
                        .addBefore("packet_handler", "lifecraft", new PacketListener(player));
            } catch (Exception e) {
                getLogger().warning("Failed to inject channel handler to player " + player.getName() + "エラー: " + e);
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player: Bukkit.getOnlinePlayers()) {
            ChannelPipeline pipeline = Utils.getChannel(player).pipeline();
            try {
                if (pipeline.get("LifeCraft") != null) {
                    pipeline.remove("lifecraft");
                }
            } catch (Exception e) {
                return;
            }
        }
    }
}
