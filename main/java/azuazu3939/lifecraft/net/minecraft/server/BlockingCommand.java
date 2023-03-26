package azuazu3939.lifecraft.net.minecraft.server;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static azuazu3939.lifecraft.net.minecraft.server.PacketListener.map;

public class BlockingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        if (map.isEmpty() || !map.containsKey(player)) {
            map.put(player, true);
            player.sendMessage(ChatColor.GREEN + "リペア、サルベージのブロックを解除しました。");
        } else {
            map.remove(player);
            player.sendMessage(ChatColor.GREEN + "リペア、サルベージをブロックしました。");
        }
        return true;
    }
}
